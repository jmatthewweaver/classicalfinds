package com.iw.cf.core.service;

import com.iw.cf.core.dao.WorkVideoDao;
import com.iw.cf.core.dto.Work;
import com.iw.cf.core.dto.WorkVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkVideoService {

    @Autowired
    private WorkVideoDao workVideoDao;

    public void insert(WorkVideo workVideo) {
        workVideoDao.insert(workVideo);
    }

    public void deleteForWork(Work work) {
        workVideoDao.deleteForWork(work.getId());
    }

    public List<WorkVideo> getForWork(Long workId) {
        return workVideoDao.getForWork(workId);
    }
}
