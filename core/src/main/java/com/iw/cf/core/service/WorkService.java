package com.iw.cf.core.service;

import com.iw.cf.core.dao.WorkDao;
import com.iw.cf.core.dto.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkService {

    @Autowired
    private WorkDao workDao;

    public void insert(Work work) {
        workDao.insert(work);
    }

    public void deleteAll() {
        workDao.deleteAll();
    }
}
