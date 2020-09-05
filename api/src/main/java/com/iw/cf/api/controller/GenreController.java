package com.iw.cf.api.controller;

import com.iw.cf.core.dto.Genre;
import com.iw.cf.core.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Genre> getGenres() {
        return genreService.getWithVideos();
    }
}
