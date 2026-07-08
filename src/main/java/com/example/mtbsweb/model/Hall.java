package com.example.mtbsweb.model;

import jakarta.persistence.*;

@Entity
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer capacity;
    private String hallType; // STANDARD, GOLD, IMAX, 4DX

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    public Hall() {}

    public Hall(String name, Integer capacity, String hallType, Theater theater) {
        this.name = name;
        this.capacity = capacity;
        this.hallType = hallType;
        this.theater = theater;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    public String getHallType() { return hallType; }
    public void setHallType(String hallType) { this.hallType = hallType; }
    public Theater getTheater() { return theater; }
    public void setTheater(Theater theater) { this.theater = theater; }
}
