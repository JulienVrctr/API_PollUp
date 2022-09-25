package com.pollup.api.controller;

import com.pollup.api.model.MyListenings;
import com.pollup.api.service.MyListeningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MyListeningsController {

    @Autowired
    private MyListeningsService myListeningsService;

    /**
     * Create - Add a new Listening
     * @param myListening An object MyListenings
     * @return The MyListenings object saved
     */
    @PostMapping("/add_my_listenings")
    public MyListenings createMyListening(@RequestBody MyListenings myListening) {
        return myListeningsService.saveMyListening(myListening);
    }

    /**
     * Update - Update an existing Listening
     * @param idartist - The id of the artist
     * @param idmusic - The id of the music
     * @param myListenings - The MyListenings object updated
     * @return
     */
    @PutMapping("/update_my_listening/{idartist}/{idmusic}")
    public MyListenings updateMyListening(@PathVariable("idartist") final Long idartist, @PathVariable("idmusic") final Long idmusic, @RequestBody MyListenings myListenings) {
        Optional<MyListenings> e = myListeningsService.getMyListeningByIdartistAndIdmusic(idartist, idmusic);
        if(e.isPresent()) {
            MyListenings currentListening = e.get();

            Long number_listenings = myListenings.getNumber();
            if(number_listenings != null) {
                currentListening.setNumber(currentListening.getNumber() + 1);
            }

            myListeningsService.saveMyListening(currentListening);
            return currentListening;
        } else {
            return myListeningsService.saveMyListening(myListenings);
        }
    }

    /**
     * Read - Get all Listenings
     * @return - An Iterable object of MyListenings full filled
     */
    @GetMapping("/my_listenings")
    public Iterable<MyListenings> getMyListenings() {
        return myListeningsService.getMyListenings();
    }

    /**
     * Read - Get one Listening
     * @param idmy_listening The id of the Listening
     * @return An MyListenings object full filled
     */
    @GetMapping("/my_listenings/{idmy_listening}")
    public MyListenings getMyListening(@PathVariable("idmy_listening") final Long idmy_listening) {
        Optional<MyListenings> myListenings = myListeningsService.getMyListening(idmy_listening);
        if(myListenings.isPresent()) {
            return myListenings.get();
        } else {
            return null;
        }
    }
}
