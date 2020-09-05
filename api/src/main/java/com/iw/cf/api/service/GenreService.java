package com.iw.cf.api.service;

import com.iw.cf.api.dao.GenreDao;
import com.iw.cf.api.dto.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    @Autowired
    private GenreDao genreDao;

    public void insert(Genre genre) {
        genreDao.insert(genre);
    }
}
