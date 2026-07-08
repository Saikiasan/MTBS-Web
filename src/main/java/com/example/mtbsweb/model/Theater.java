package com.example.mtbsweb.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String city;
    private String phone;
    private String imageUrl;
    
    @Column(length = 1000)
    private String facilities; // Comma separated list of facilities

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<Hall> halls;

    public Theater() {}

    public Theater(String name, String address, String city, String phone, String imageUrl, String facilities) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.facilities = facilities;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getFacilities() { return facilities; }
    public void setFacilities(String facilities) { this.facilities = facilities; }
    public List<Hall> getHalls() { return halls; }
    public void setHalls(List<Hall> halls) { this.halls = halls; }
}
