package com.iw.cf.core.service;

import com.iw.cf.core.dao.WorkDao;
import com.iw.cf.core.dto.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Work> getRandomSampling(int limit) {
        return workDao.getRandomSampling(limit);
    }
}
