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

    public MovieController(MovieService movieService, BookingRepository bookingRepository) {
        this.movieService = movieService;
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("movies", movieService.getMovies());
        return "index";
    }

    @GetMapping("/book/{id}")
    public String bookMovie(@PathVariable Long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        if (movie == null) return "redirect:/";
        model.addAttribute("movie", movie);
        return "book";
    }

    @PostMapping("/book/confirm")
    public String confirmBooking(@RequestParam String customerName,
                                 @RequestParam String movieTitle,
                                 @RequestParam String seats,
                                 @RequestParam Double totalPrice,
                                 RedirectAttributes redirectAttributes) {
        Booking booking = new Booking(customerName, movieTitle, seats, totalPrice);
        bookingRepository.save(booking);
        redirectAttributes.addFlashAttribute("message", "Booking Confirmed Successfully for " + movieTitle + "! Enjoy the show.");
        return "redirect:/";
    }
}
