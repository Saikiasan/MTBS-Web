package com.example.mtbsweb.model;

import jakarta.persistence.*;

@Entity
public class Movie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private Double rating;
    private String image;
    private String imdbUrl;
    
    @Column(length = 1000)
    private String description;

    public Movie() {
    }

    public Movie(String title, Double rating, String image, String imdbUrl, String description) {
        this.title = title;
        this.rating = rating;
        this.image = image;
        this.imdbUrl = imdbUrl;
        this.description = description;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getImdbUrl() {
        return imdbUrl;
    }
    public void setImdbUrl(String imdbUrl) {
        this.imdbUrl = imdbUrl;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
