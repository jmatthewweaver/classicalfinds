package com.iw.cf.api;

import lombok.extern.java.Log;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Log
public class ApiApplication {

	@Autowired
	private Flyway flyway;

	@PostConstruct
	public void init() {
		try {
			flyway.validate();
		} catch(FlywayException e) {
			log.info("Flyway validation failed, repairing");
			flyway.repair();
			flyway.migrate();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
