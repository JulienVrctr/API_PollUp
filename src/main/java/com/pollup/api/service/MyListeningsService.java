package com.pollup.api.service;

import com.pollup.api.model.MyListenings;
import com.pollup.api.repository.MyListeningsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class MyListeningsService {

    @Autowired
    private MyListeningsRepository myListeningsRepository;

    public Optional<MyListenings> getMyListening(final Long idmy_listening){
        return myListeningsRepository.findById(idmy_listening);
    }

    public Optional<MyListenings> getMyListeningByIdartistAndIdmusic(final Long idartist, final Long idmusic){
        return myListeningsRepository.findByIdartistAndIdmusic(idartist, idmusic);
    }

    public Iterable<MyListenings> getMyListenings() {
        return myListeningsRepository.findAll();
    }

    public MyListenings saveMyListening(MyListenings my_listening) {
        MyListenings savedMyListenings = myListeningsRepository.save(my_listening);
        return savedMyListenings;
    }
}
