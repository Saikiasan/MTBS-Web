package com.example.mtbsweb.controller;

import com.example.mtbsweb.model.Theater;
import com.example.mtbsweb.service.TheaterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TheaterController {
    private final TheaterService theaterService;

    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @GetMapping("/theaters")
    public String listTheaters(Model model) {
        model.addAttribute("theaters", theaterService.getAllTheaters());
        return "theaters";
    }

    @GetMapping("/theaters/{id}")
    public String theaterDetails(@PathVariable Long id, Model model) {
        Theater theater = theaterService.getTheaterById(id);
        if (theater == null) return "redirect:/theaters";
        model.addAttribute("theater", theater);
        return "theater-detail";
    }
}
