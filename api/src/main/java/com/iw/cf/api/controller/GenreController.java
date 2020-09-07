package com.iw.cf.api.controller;

import com.iw.cf.core.dto.Composer;
import com.iw.cf.core.dto.Genre;
import com.iw.cf.core.dto.Work;
import com.iw.cf.core.service.ComposerService;
import com.iw.cf.core.service.GenreService;
import com.iw.cf.core.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @Autowired
    private ComposerService composerService;

    @Autowired
    private WorkService workService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Genre> getGenres() {
        return genreService.getWithVideos();
    }

    @RequestMapping(value = "/{genreId}/composers", method = RequestMethod.GET)
    @ResponseBody
    public List<Composer> getGenreComposers(@PathVariable("genreId") Long genreId) {
        return composerService.getByGenre(genreId);
    }

    @RequestMapping(value = "/{genreId}/composers/{composerId}/works", method = RequestMethod.GET)
    @ResponseBody
    public List<Work> getByGenreAndComposer(@PathVariable("genreId") Long genreId, @PathVariable("composerId") Long composerId) {
        return workService.search(null, genreId, composerId);
    }
}
