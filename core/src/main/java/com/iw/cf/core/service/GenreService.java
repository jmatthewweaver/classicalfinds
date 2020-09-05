package com.iw.cf.core.service;

import com.iw.cf.core.dao.GenreDao;
import com.iw.cf.core.dto.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    @Autowired
    private GenreDao genreDao;

    public void insert(Genre genre) {
        genreDao.insert(genre);
    }

    public void deleteAll() {
        genreDao.deleteAll();
    }
}
