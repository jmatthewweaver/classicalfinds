package com.iw.cf.api;

import com.iw.cf.api.service.GenreService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Log
public class Ingestor {

    @Value("${ingestor.dataFilename}")
    private String dataFilename;

    @Autowired
    private GenreService genreService;

    @PostConstruct
    public void init() {

    }

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
