package com.iw.cf.api.controller;

import com.iw.cf.core.dto.WorkVideo;
import com.iw.cf.core.service.WorkVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/works")
public class WorkController {

    @Autowired
    private WorkVideoService workVideoService;

    @RequestMapping(value = "/{workId}/videos", method = RequestMethod.GET)
    @ResponseBody
    public List<WorkVideo> getWorkVideos(@PathVariable("workId") Long workId) {
        return workVideoService.getForWork(workId);
    }
}
