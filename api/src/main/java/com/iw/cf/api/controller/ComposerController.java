package com.iw.cf.api.controller;

import com.iw.cf.core.dto.Composer;
import com.iw.cf.core.dto.Work;
import com.iw.cf.core.service.ComposerService;
import com.iw.cf.core.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/composers")
public class ComposerController {

    @Autowired
    private ComposerService composerService;

    @Autowired
    private WorkService workService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Composer> getComposers() {
        return composerService.getWithVideos();
    }

    @RequestMapping(value = "/{composerId}/works", method = RequestMethod.GET)
    @ResponseBody
    public List<Work> getComposerWorks(@PathVariable("composerId") Long composerId) {
        return workService.search(null, composerId);
    }
}
