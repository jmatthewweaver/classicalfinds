package com.iw.cf.core.service;

import com.iw.cf.core.dao.ComposerDao;
import com.iw.cf.core.dto.Composer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComposerService {

    @Autowired
    private ComposerDao composerDao;

    public void insert(Composer composer) {
        composerDao.insert(composer);
    }

    public void deleteAll() {
        composerDao.deleteAll();
    }

    public Composer getById(Long id) {
        return composerDao.getById(id);
    }

    public List<Composer> getByGenre(Long genreId) {
        return composerDao.getByGenre(genreId);
    }

    public List<Composer> getByEra(Long eraId) {
        return composerDao.getByEra(eraId);
    }

    public List<Composer> getWithVideos() {
        return composerDao.getWithVideos();
    }
}
