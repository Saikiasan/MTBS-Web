package com.example.mtbsweb.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookingRef;
    
    @ManyToOne
    @JoinColumn(name = "showtime_id")
    private Showtime showtime;

    private String customerName;
    private String email;
    private String phone;
    
    // Storing these directly so if showtime is deleted, booking is somewhat preserved
    private String movieTitle;
    private String theaterName;
    private String hallName;
    
    private String seats;
    private Double totalPrice;
    private LocalDateTime bookingDate;

    public Booking() {}

    public Booking(String bookingRef, Showtime showtime, String customerName, String email, String phone, 
                   String movieTitle, String theaterName, String hallName, String seats, Double totalPrice, LocalDateTime bookingDate) {
        this.bookingRef = bookingRef;
        this.showtime = showtime;
        this.customerName = customerName;
        this.email = email;
        this.phone = phone;
        this.movieTitle = movieTitle;
        this.theaterName = theaterName;
        this.hallName = hallName;
        this.seats = seats;
        this.totalPrice = totalPrice;
        this.bookingDate = bookingDate;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBookingRef() { return bookingRef; }
    public void setBookingRef(String bookingRef) { this.bookingRef = bookingRef; }
    public Showtime getShowtime() { return showtime; }
    public void setShowtime(Showtime showtime) { this.showtime = showtime; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getMovieTitle() { return movieTitle; }
    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }
    public String getTheaterName() { return theaterName; }
    public void setTheaterName(String theaterName) { this.theaterName = theaterName; }
    public String getHallName() { return hallName; }
    public void setHallName(String hallName) { this.hallName = hallName; }
    public String getSeats() { return seats; }
    public void setSeats(String seats) { this.seats = seats; }
    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
    public LocalDateTime getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }
}
