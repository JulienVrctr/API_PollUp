package com.pollup.api.repository;

import com.pollup.api.model.MusicOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicOfWeekRepository extends JpaRepository<MusicOfWeek, Long> {

    List<MusicOfWeek> findByStartWeekAndEndWeek(String start_week, String end_week);
}
