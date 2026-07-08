package com.example.mtbsweb.service;

import com.example.mtbsweb.model.Theater;
import com.example.mtbsweb.repository.TheaterRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TheaterService {
    private final TheaterRepository theaterRepository;

    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    public Theater getTheaterById(Long id) {
        return theaterRepository.findById(id).orElse(null);
    }
}
