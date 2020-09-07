package com.iw.cf.api.controller;

import com.iw.cf.api.dto.SearchResults;
import com.iw.cf.core.service.ComposerService;
import com.iw.cf.core.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

    @Autowired
    private ComposerService composerService;

    @Autowired
    private WorkService workService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public SearchResults search(@RequestParam("q") String query) {
        SearchResults searchResults = new SearchResults();

        searchResults.setComposers(composerService.search(query));
        searchResults.setWorks(workService.search(query, null, null, null));

        return searchResults;
    }
}
