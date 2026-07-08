package com.example.mtbsweb.service;

import com.example.mtbsweb.model.Showtime;
import com.example.mtbsweb.repository.ShowtimeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.LocalDate;

@Service
public class ShowtimeService {
    private final ShowtimeRepository showtimeRepository;

    public ShowtimeService(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    public List<Showtime> getShowtimesByMovieId(Long movieId) {
        return showtimeRepository.findByMovieId(movieId);
    }

    public Showtime getShowtimeById(Long id) {
        return showtimeRepository.findById(id).orElse(null);
    }

    public Map<LocalDate, List<Showtime>> getGroupedShowtimesByMovie(Long movieId) {
        List<Showtime> showtimes = getShowtimesByMovieId(movieId);
        return showtimes.stream().collect(Collectors.groupingBy(Showtime::getShowDate));
    }
}
