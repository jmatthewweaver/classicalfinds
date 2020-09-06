package com.iw.cf.workdecoder;

import com.iw.cf.core.dto.WorkVideo;
import com.iw.cf.core.service.WorkVideoService;
import lombok.extern.java.Log;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.iw.cf"})
@Log
public class WorkdecoderApplication
implements CommandLineRunner {

	@Autowired
	private WorkVideoService workVideoService;

	public static void main(String[] args) {
		SpringApplication.run(WorkdecoderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<WorkVideo> workVideoList = workVideoService.getAll();
		for(WorkVideo workVideo: workVideoList) {
			log.info(workVideo.getTitle() + " -> " + StringEscapeUtils.unescapeHtml4(workVideo.getTitle()));
			log.info(workVideo.getDescription() + " -> " + StringEscapeUtils.unescapeHtml4(workVideo.getDescription()));

			workVideo.setTitle(StringEscapeUtils.unescapeHtml4(workVideo.getTitle()));
			workVideo.setDescription(StringEscapeUtils.unescapeHtml4(workVideo.getDescription()));
			workVideoService.update(workVideo);
		}
	}
}
