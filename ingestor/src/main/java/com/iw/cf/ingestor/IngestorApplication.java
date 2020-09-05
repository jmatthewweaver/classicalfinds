package com.iw.cf.ingestor;

import com.google.gson.Gson;
import com.iw.cf.core.dto.Genre;
import com.iw.cf.core.service.GenreService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootApplication(scanBasePackages = {"com.iw.cf"})
@Log
public class IngestorApplication
implements CommandLineRunner {

	@Value("${ingestor.dataFilename}")
	private String dataFilename;

	@Autowired
	private GenreService genreService;

	@Override
	public void run(String... args) throws FileNotFoundException {
		Gson gson = new Gson();
		Map<String, Object> props = gson.fromJson(new FileReader(dataFilename), Map.class);

		List<Map<String, Object> > composers = (List<Map<String, Object> >) props.get("composers");
		Set<String> genres = new HashSet<>();

		for(Map<String, Object> composer: composers) {
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
	}

	public static void main(String[] args) {
		SpringApplication.run(IngestorApplication.class, args);
	}

}
