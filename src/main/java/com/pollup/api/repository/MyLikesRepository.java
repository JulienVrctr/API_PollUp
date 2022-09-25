package com.pollup.api.repository;

import com.pollup.api.model.MyLikes;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface MyLikesRepository extends JpaRepository<MyLikes, Long> {

    List<MyLikes> findByIdartist(Long idartist);

    @Transactional
    void deleteByIdartistAndIdmusic(Long idartist, Long idmusic);
}