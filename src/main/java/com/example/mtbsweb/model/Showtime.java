package com.example.mtbsweb.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    private LocalDate showDate;
    private LocalTime showTime;
    private Double price;

    public Showtime() {}

    public Showtime(Movie movie, Hall hall, LocalDate showDate, LocalTime showTime, Double price) {
        this.movie = movie;
        this.hall = hall;
        this.showDate = showDate;
        this.showTime = showTime;
        this.price = price;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }
    public Hall getHall() { return hall; }
    public void setHall(Hall hall) { this.hall = hall; }
    public LocalDate getShowDate() { return showDate; }
    public void setShowDate(LocalDate showDate) { this.showDate = showDate; }
    public LocalTime getShowTime() { return showTime; }
    public void setShowTime(LocalTime showTime) { this.showTime = showTime; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
