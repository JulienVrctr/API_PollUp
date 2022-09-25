package com.pollup.api.repository;

import com.pollup.api.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {

    List<Music> findByTitleContaining(String title);

    @Query(value = "SELECT M.idmusic, M.idartist, M.title, M.data, M.idproject FROM musics M INNER JOIN pollmusic P ON M.idmusic = P.idmusic WHERE P.date >= ?1 AND P.date <= ?2 GROUP BY P.idmusic ORDER BY COUNT(P.idmusic) DESC LIMIT 5;",
            nativeQuery = true)
    List<Music> getFiveMostPoll(String startDate, String endDate);

    @Query(value = "SELECT M.idmusic, M.idartist, M.title, M.data, M.idproject FROM musics M INNER JOIN featuring_artist F ON M.idmusic = F.idmusic WHERE F.idartist = ?1 GROUP BY M.idproject;", nativeQuery = true)
    List<Music> findFeaturingByIdartist(Long idartist);
}
