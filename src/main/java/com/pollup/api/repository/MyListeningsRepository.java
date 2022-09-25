package com.pollup.api.repository;

import com.pollup.api.model.Artist;
import com.pollup.api.model.MyListenings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyListeningsRepository extends JpaRepository<MyListenings, Long> {

    Optional<MyListenings> findByIdartistAndIdmusic(Long idartist, Long idmusic);

}
