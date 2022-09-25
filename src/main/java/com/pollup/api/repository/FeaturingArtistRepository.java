package com.pollup.api.repository;

import com.pollup.api.model.FeaturingArtist;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface FeaturingArtistRepository  extends JpaRepository<FeaturingArtist, Long> {

    @Transactional
    void deleteByIdartistAndIdmusic(Long idartist, Long idmusic);


}
