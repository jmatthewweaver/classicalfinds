package com.iw.cf.ingestor;

import com.google.gson.Gson;
import com.iw.cf.core.dto.Composer;
import com.iw.cf.core.dto.Era;
import com.iw.cf.core.dto.Genre;
import com.iw.cf.core.service.ComposerService;
import com.iw.cf.core.service.EraService;
import com.iw.cf.core.service.GenreService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootApplication(scanBasePackages = {"com.iw.cf"})
@Log
public class IngestorApplication
implements CommandLineRunner {

	private static final String[] ERA_ORDERS = new String[] {
			"Medieval",
			"Renaissance",
			"Baroque",
			"Classical",
			"Early Romantic",
			"Romantic",
			"Late Romantic",
			"20th Century",
			"Post-War",
			"21st Century"
	};

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	@Value("${ingestor.dataFilename}")
	private String dataFilename;

	@Autowired
	private GenreService genreService;

	@Autowired
	private EraService eraService;

	@Autowired
	private ComposerService composerService;

	@Override
	public void run(String... args) throws FileNotFoundException {
		Gson gson = new Gson();
		Map<String, Object> props = gson.fromJson(new FileReader(dataFilename), Map.class);

		List<Map<String, Object> > composers = (List<Map<String, Object> >) props.get("composers");
		Set<String> genres = new HashSet<>();
		Map<String, Genre> genresByName = new HashMap<>();
		Set<String> eras = new HashSet<>();
		Map<String, Era> erasByName = new HashMap<>();

		for(Map<String, Object> composer: composers) {
			eras.add((String) composer.get("epoch"));
			List<Map<String, Object> > works = (List<Map<String, Object>>) composer.get("works");
			for(Map<String, Object> work: works) {
				genres.add((String) work.get("genre"));
			}
		}

		log.info("Creating genres...");
		genreService.deleteAll();
		for(String genreName: genres) {
			Genre genre = new Genre();
			genre.setName(genreName);
			genreService.insert(genre);
			log.info("Created genre '" + genreName + "'");
			genresByName.put(genreName, genre);
		}

		log.info("Creating eras...");
		eraService.deleteAll();
		for(String eraName: eras) {
			Era era = new Era();
			era.setName(eraName);
			era.setSortOrder(Arrays.asList(ERA_ORDERS).indexOf(eraName));
			eraService.insert(era);
			log.info("Created era '" + eraName + "' with order " + era.getSortOrder());
			erasByName.put(eraName, era);
		}

		log.info("Creating composers...");
		composerService.deleteAll();
		for(Map<String, Object> composerInfo: composers) {
			Composer composer = new Composer();
			composer.setName((String) composerInfo.get("name"));
			composer.setCompleteName((String) composerInfo.get("complete_name"));
			composer.setEraId(erasByName.get(composerInfo.get("epoch")).getId());
			String birthDate = (String) composerInfo.get("birth");
			if(birthDate != null) {
				try {
					composer.setBirthDate(DATE_FORMAT.parse(birthDate));
				} catch (ParseException e) {
					log.info("Invalid birth date '" + birthDate + "'");
				}
			}

			String deathDate = (String) composerInfo.get("death");
			if(deathDate != null) {
				try {
					composer.setDeathDate(DATE_FORMAT.parse(deathDate));
				} catch(ParseException e) {
					log.info("Invalid death date '" + deathDate + "'");
				}
			}

			composer.setPopular("1".equals(composerInfo.get("popular")));
			composer.setRecommended("1".equals(composerInfo.get("recommended")));

			composerService.insert(composer);
			log.info("Created composer '" + composer.getName() + "'");
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(IngestorApplication.class, args);
	}

}
