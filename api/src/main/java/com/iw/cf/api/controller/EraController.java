package com.iw.cf.api.controller;

import com.iw.cf.core.dto.Composer;
import com.iw.cf.core.dto.Era;
import com.iw.cf.core.service.ComposerService;
import com.iw.cf.core.service.EraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/eras")
public class EraController {

    @Autowired
    private EraService eraService;

    @Autowired
    private ComposerService composerService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Era> getEras() {
        return eraService.getWithVideos();
    }

    @RequestMapping(value = "/{eraId}/composers", method = RequestMethod.GET)
    @ResponseBody
    public List<Composer> getEraComposers(@PathVariable("eraId") Long eraId) {
        return composerService.getByEra(eraId);
    }
}
