package com.pollup.api.repository;

import com.pollup.api.model.Artist;
import com.pollup.api.model.ArtistDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    List<Artist> findByNameContaining(String name);
    Optional<Artist> findByEmail(String Email);

    Optional<Artist> findByEmailAndPassword(String mail, String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE `artist` SET data = ?1 , name = ?2 , email = ?3 , phone = ?4 , avatar = ?5 WHERE idartist = ?6 ;", nativeQuery = true)
    void updateArtist(byte[] file, String name, String mail, String phone, String avatar, Long idartist);

    @Modifying
    @Transactional
    @Query(value = "UPDATE `artist` SET nb_polls = nb_polls + 1 WHERE idartist = ?1 ;", nativeQuery = true)
    void updateNbpolls(Long idartist);

    @Query(value = "SELECT A.name FROM artist A INNER JOIN featuring_artist F ON A.idartist = F.idartist WHERE F.idmusic = ?1", nativeQuery = true)
    String findNameartistFeaturingByIdmusic(Long idmusic);
}
