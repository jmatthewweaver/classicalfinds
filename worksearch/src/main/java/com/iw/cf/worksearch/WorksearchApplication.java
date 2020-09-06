package com.iw.cf.worksearch;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.iw.cf.core.dto.Composer;
import com.iw.cf.core.dto.Work;
import com.iw.cf.core.dto.WorkVideo;
import com.iw.cf.core.service.ComposerService;
import com.iw.cf.core.service.WorkService;
import com.iw.cf.core.service.WorkVideoService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.iw.cf"})
@Log
public class WorksearchApplication
implements CommandLineRunner {

	@Value("${youtube.data.apiKey}")
	private String youtubeDataApiKey;

	@Autowired
	private WorkService workService;

	@Autowired
	private ComposerService composerService;

	@Autowired
	private WorkVideoService workVideoService;

	public static void main(String[] args) {
		SpringApplication.run(WorksearchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		YouTube youTube = new YouTube.Builder(GoogleNetHttpTransport.newTrustedTransport(),
				JacksonFactory.getDefaultInstance(),
				new HttpRequestInitializer() {
					@Override
					public void initialize(HttpRequest httpRequest) throws IOException {
					}
				})
				.setYouTubeRequestInitializer(new YouTubeRequestInitializer(youtubeDataApiKey))
				.setApplicationName("ClassicalFinds")
				.build();
		List<Work> works = workService.getWorksToProcess();
		for(Work work: works) {
			workVideoService.deleteForWork(work);
			Composer composer = composerService.getById(work.getComposerId());
			log.info("Searching for performances of work '" + work.getTitle() + "' by '" + composer.getCompleteName() + "'");

			String searchQuery = composer.getCompleteName() + " " + work.getTitle() + " " + work.getSubtitle();

			YouTube.Search.List list = youTube.search().list(Arrays.asList("snippet"))
					.setQ(searchQuery)
					.setOrder("viewCount")
					.setType(Arrays.asList("video"));
			SearchListResponse response = list.execute();
			if(response.getItems().isEmpty()) {
				log.info("  No results found!");
				log.info("");
			} else {
				int seq = 0;
				for (SearchResult searchResult : response.getItems()) {
					log.info("  " + searchResult.getSnippet().getTitle());
					log.info("  http://youtube.com/watch?v=" + searchResult.getId().getVideoId());
					log.info("");

					WorkVideo workVideo = new WorkVideo();
					workVideo.setWorkId(work.getId());
					workVideo.setTitle(searchResult.getSnippet().getTitle());
					workVideo.setDescription(searchResult.getSnippet().getDescription());
					workVideo.setVideoId(searchResult.getId().getVideoId());
					workVideo.setSortOrder(seq++);
					workVideoService.insert(workVideo);
				}
			}

		}
	}
}
