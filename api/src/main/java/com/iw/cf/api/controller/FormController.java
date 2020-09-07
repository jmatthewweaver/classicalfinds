package com.iw.cf.api.controller;

import com.iw.cf.core.dto.Composer;
import com.iw.cf.core.dto.Form;
import com.iw.cf.core.dto.Work;
import com.iw.cf.core.service.ComposerService;
import com.iw.cf.core.service.FormService;
import com.iw.cf.core.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/forms")
public class FormController {

    @Autowired
    private FormService formService;

    @Autowired
    private ComposerService composerService;

    @Autowired
    private WorkService workService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Form> getForms() {
        return formService.getWithVideos();
    }

    @RequestMapping(value = "/{formId}/composers", method = RequestMethod.GET)
    @ResponseBody
    public List<Composer> getFormComposers(@PathVariable("formId") Long formId) {
        return composerService.getByForm(formId);
    }

    @RequestMapping(value = "/{formId}/composers/{composerId}/works", method = RequestMethod.GET)
    @ResponseBody
    public List<Work> getByFormAndComposer(@PathVariable("formId") Long formId, @PathVariable("composerId") Long composerId) {
        return workService.search(formId, null, composerId);
    }
}
