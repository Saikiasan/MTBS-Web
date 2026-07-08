package com.example.mtbsweb.repository;

import com.example.mtbsweb.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    List<Showtime> findByMovieId(Long movieId);
    List<Showtime> findByHall_Theater_Id(Long theaterId);
}
