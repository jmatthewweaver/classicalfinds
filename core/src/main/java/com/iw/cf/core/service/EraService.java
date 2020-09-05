package com.iw.cf.core.service;

import com.iw.cf.core.dao.EraDao;
import com.iw.cf.core.dto.Era;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EraService {

    @Autowired
    private EraDao eraDao;

    public void insert(Era era) {
        eraDao.insert(era);
    }

    public void deleteAll() {
        eraDao.deleteAll();
    }
}
