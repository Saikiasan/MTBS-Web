package com.example.mtbsweb.controller;

import com.example.mtbsweb.model.Movie;
import com.example.mtbsweb.repository.BookingRepository;
import com.example.mtbsweb.repository.TheaterRepository;
import com.example.mtbsweb.repository.ShowtimeRepository;
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
    private final TheaterRepository theaterRepository;
    private final ShowtimeRepository showtimeRepository;

    public AdminController(MovieService movieService, BookingRepository bookingRepository, 
                           TheaterRepository theaterRepository, ShowtimeRepository showtimeRepository) {
        this.movieService = movieService;
        this.bookingRepository = bookingRepository;
        this.theaterRepository = theaterRepository;
        this.showtimeRepository = showtimeRepository;
    }

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("totalMovies", movieService.getMovies().size());
        model.addAttribute("totalBookings", bookingRepository.count());
        model.addAttribute("totalTheaters", theaterRepository.count());
        // For revenue, we can sum prices, but for now just pass bookings to UI to let JS calculate, or do simple aggregation.
        model.addAttribute("bookings", bookingRepository.findAll());
        return "admin-dashboard";
    }

    @GetMapping("/bookings")
    public String viewBookings(Model model) {
        model.addAttribute("bookings", bookingRepository.findAll());
        return "admin-bookings";
    }

    @GetMapping("/movies")
    public String viewMovies(Model model) {
        model.addAttribute("movies", movieService.getMovies());
        return "admin-movies";
    }

    @GetMapping("/theaters")
    public String viewTheaters(Model model) {
        model.addAttribute("theaters", theaterRepository.findAll());
        return "admin-theaters";
    }

    @GetMapping("/showtimes")
    public String viewShowtimes(Model model) {
        model.addAttribute("showtimes", showtimeRepository.findAll());
        return "admin-showtimes";
    }

    // --- Movie CRUD ---
    @GetMapping("/movies/new")
    public String showCreateForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "admin-movie-form";
    }

    @PostMapping("/movies")
    public String saveMovie(@ModelAttribute Movie movie, RedirectAttributes redirectAttributes) {
        movieService.saveMovie(movie);
        redirectAttributes.addFlashAttribute("message", "Movie saved successfully!");
        return "redirect:/admin/movies";
    }

    @GetMapping("/movies/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        if (movie == null) {
            return "redirect:/admin/movies";
        }
        model.addAttribute("movie", movie);
        return "admin-movie-form";
    }

    @PostMapping("/movies/update/{id}")
    public String updateMovie(@PathVariable Long id, @ModelAttribute Movie movie, RedirectAttributes redirectAttributes) {
        movie.setId(id);
        movieService.saveMovie(movie);
        redirectAttributes.addFlashAttribute("message", "Movie updated successfully!");
        return "redirect:/admin/movies";
    }

    @PostMapping("/movies/delete/{id}")
    public String deleteMovie(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        movieService.deleteMovie(id);
        redirectAttributes.addFlashAttribute("message", "Movie deleted successfully!");
        return "redirect:/admin/movies";
    }
}
