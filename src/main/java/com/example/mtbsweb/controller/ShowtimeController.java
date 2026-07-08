package com.example.mtbsweb.controller;

import com.example.mtbsweb.model.Showtime;
import com.example.mtbsweb.service.ShowtimeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShowtimeController {
    private final ShowtimeService showtimeService;
    private final com.example.mtbsweb.repository.BookingRepository bookingRepository;

    public ShowtimeController(ShowtimeService showtimeService, com.example.mtbsweb.repository.BookingRepository bookingRepository) {
        this.showtimeService = showtimeService;
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/showtime/{id}/book")
    public String bookShowtime(@PathVariable Long id, Model model) {
        Showtime showtime = showtimeService.getShowtimeById(id);
        if (showtime == null) return "redirect:/";
        model.addAttribute("showtime", showtime);
        return "book";
    }

    @org.springframework.web.bind.annotation.PostMapping("/book/confirm")
    public String confirmBooking(@org.springframework.web.bind.annotation.RequestParam Long showtimeId,
                                 @org.springframework.web.bind.annotation.RequestParam String customerName,
                                 @org.springframework.web.bind.annotation.RequestParam String email,
                                 @org.springframework.web.bind.annotation.RequestParam String phone,
                                 @org.springframework.web.bind.annotation.RequestParam String seats,
                                 @org.springframework.web.bind.annotation.RequestParam Double totalPrice,
                                 org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes) {
        
        Showtime showtime = showtimeService.getShowtimeById(showtimeId);
        if(showtime == null) return "redirect:/";
        
        String bookingRef = "CINE-" + java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        
        com.example.mtbsweb.model.Booking booking = new com.example.mtbsweb.model.Booking(
            bookingRef, showtime, customerName, email, phone,
            showtime.getMovie().getTitle(), showtime.getHall().getTheater().getName(), showtime.getHall().getName(),
            seats, totalPrice, java.time.LocalDateTime.now()
        );
        
        bookingRepository.save(booking);
        
        redirectAttributes.addFlashAttribute("bookingRef", bookingRef);
        redirectAttributes.addFlashAttribute("booking", booking);
        
        return "redirect:/booking-confirmation";
    }

    @GetMapping("/booking-confirmation")
    public String bookingConfirmation() {
        return "booking-confirmation";
    }
}
