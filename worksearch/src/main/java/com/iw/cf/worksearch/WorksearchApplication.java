package com.iw.cf.worksearch;

import com.iw.cf.core.dto.Work;
import com.iw.cf.core.service.WorkService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.iw.cf"})
@Log
public class WorksearchApplication
implements CommandLineRunner {

	@Autowired
	private WorkService workService;

	public static void main(String[] args) {
		SpringApplication.run(WorksearchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Work> works = workService.getRandomSampling(50);
		for(Work work: works) {
			log.info("Got work '" + work.getTitle() + "'");
		}
	}
}
