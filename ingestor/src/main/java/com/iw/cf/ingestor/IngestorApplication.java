package com.iw.cf.ingestor;

import com.google.gson.Gson;
import com.iw.cf.core.dto.Era;
import com.iw.cf.core.dto.Genre;
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
import java.util.Arrays;
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


	@Value("${ingestor.dataFilename}")
	private String dataFilename;

	@Autowired
	private GenreService genreService;

	@Autowired
	private EraService eraService;

	@Override
	public void run(String... args) throws FileNotFoundException {
		Gson gson = new Gson();
		Map<String, Object> props = gson.fromJson(new FileReader(dataFilename), Map.class);

		List<Map<String, Object> > composers = (List<Map<String, Object> >) props.get("composers");
		Set<String> genres = new HashSet<>();
		Set<String> eras = new HashSet<>();

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
		}

		log.info("Creating eras...");
		eraService.deleteAll();
		for(String eraName: eras) {
			Era era = new Era();
			era.setName(eraName);
			era.setSortOrder(Arrays.asList(ERA_ORDERS).indexOf(eraName));
			eraService.insert(era);
			log.info("Created era '" + eraName + "' with order " + era.getSortOrder());
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(IngestorApplication.class, args);
	}

}
