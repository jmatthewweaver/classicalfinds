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

    public List<Work> getWorksToProcess() {
        return workDao.getWorksToProcess();
    }

    public List<Work> search(String query, Long formId, Long genreId, Long composerId) {
        return workDao.search(query, formId, genreId, composerId);
    }

    public List<Work> getAll() {
        return workDao.getAll();
    }

    public void clearForms() {
        workDao.clearForms();
    }

    public void update(Work work) {
        workDao.update(work);
    }
}
