package com.example.mtbsweb.controller;

import com.example.mtbsweb.model.Booking;
import com.example.mtbsweb.model.Movie;
import com.example.mtbsweb.repository.BookingRepository;
import com.example.mtbsweb.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MovieController {

    private final MovieService movieService;
    private final BookingRepository bookingRepository;
    private final com.example.mtbsweb.service.ShowtimeService showtimeService;

    public MovieController(MovieService movieService, BookingRepository bookingRepository, com.example.mtbsweb.service.ShowtimeService showtimeService) {
        this.movieService = movieService;
        this.bookingRepository = bookingRepository;
        this.showtimeService = showtimeService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("movies", movieService.getMovies());
        return "index";
    }

    @GetMapping("/movies/{id}")
    public String movieDetails(@PathVariable Long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        if (movie == null) return "redirect:/";
        model.addAttribute("movie", movie);
        model.addAttribute("groupedShowtimes", showtimeService.getGroupedShowtimesByMovie(id));
        return "movie-detail";
    }
}
