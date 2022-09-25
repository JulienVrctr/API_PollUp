package com.pollup.api.service;

import com.pollup.api.model.MyLikes;
import com.pollup.api.repository.MyLikesRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class MyLikesService {

    @Autowired
    private MyLikesRepository myLikesRepository;

    public Optional<MyLikes> getMyLike(final Long idmy_likes){
        return myLikesRepository.findById(idmy_likes);
    }

    public Iterable<MyLikes> getMyLikes() {
        return myLikesRepository.findAll();
    }

    public void deleteMyLike(final Long idartist, final Long idmusic) {
        myLikesRepository.deleteByIdartistAndIdmusic(idartist, idmusic);
    }

    public MyLikes saveMyLike(MyLikes my_likes) {
        MyLikes savedMyLikes = myLikesRepository.save(my_likes);
        return savedMyLikes;
    }

}
