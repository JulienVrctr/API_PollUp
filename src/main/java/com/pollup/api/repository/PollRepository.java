package com.pollup.api.repository;

import com.pollup.api.model.Artist;
import com.pollup.api.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface PollRepository extends JpaRepository<Poll, Long> {
    Optional<Poll> findByIdproject_StyleAndDateLessThanEqualAndDateGreaterThanEqualAndIdartist(String style, String endDate, String startDate, Artist idartist);

    @Transactional
    void deleteByIdartistAndDate(Artist idartist, String date);
}
