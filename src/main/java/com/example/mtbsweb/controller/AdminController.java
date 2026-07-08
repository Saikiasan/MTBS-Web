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
@RequestMapping("/admin")
public class AdminController {

    private final MovieService movieService;
    private final BookingRepository bookingRepository;

    public AdminController(MovieService movieService, BookingRepository bookingRepository) {
        this.movieService = movieService;
        this.bookingRepository = bookingRepository;
    }

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("movies", movieService.getMovies());
        model.addAttribute("bookings", bookingRepository.findAll());
        return "admin-dashboard";
    }

    @GetMapping("/movies/new")
    public String showCreateForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "admin-movie-form";
    }

    @PostMapping("/movies")
    public String saveMovie(@ModelAttribute Movie movie, RedirectAttributes redirectAttributes) {
        movieService.saveMovie(movie);
        redirectAttributes.addFlashAttribute("message", "Movie saved successfully!");
        return "redirect:/admin";
    }

    @GetMapping("/movies/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        if (movie == null) {
            return "redirect:/admin";
        }
        model.addAttribute("movie", movie);
        return "admin-movie-form";
    }

    @PostMapping("/movies/update/{id}")
    public String updateMovie(@PathVariable Long id, @ModelAttribute Movie movie, RedirectAttributes redirectAttributes) {
        movie.setId(id);
        movieService.saveMovie(movie);
        redirectAttributes.addFlashAttribute("message", "Movie updated successfully!");
        return "redirect:/admin";
    }

    @PostMapping("/movies/delete/{id}")
    public String deleteMovie(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        movieService.deleteMovie(id);
        redirectAttributes.addFlashAttribute("message", "Movie deleted successfully!");
        return "redirect:/admin";
    }
}
