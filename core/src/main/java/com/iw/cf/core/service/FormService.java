package com.iw.cf.core.service;

import com.iw.cf.core.dao.FormDao;
import com.iw.cf.core.dto.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService {
    @Autowired
    private FormDao formDao;

    public void insert(Form form) {
        formDao.insert(form);
    }

    public void deleteAll() {
        formDao.deleteAll();
    }

    public List<Form> getWithVideos() {
        return formDao.getWithVideos();
    }
}
