package com.iw.cf.core.service;

import com.iw.cf.core.dao.ComposerDao;
import com.iw.cf.core.dto.Composer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
