package com.pollup.api.controller;

import com.pollup.api.model.MyLikes;
import com.pollup.api.service.MyLikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MyLikesController {

    @Autowired
    private MyLikesService myLikesService;

    /**
     * Create - Add a new Like
     * @param myLikes An object MyLikes
     * @return The MyLikes object saved
     */
    @PostMapping("/add_my_likes")
    public MyLikes createMyLike(@RequestBody MyLikes myLikes) {
        return myLikesService.saveMyLike(myLikes);
    }

    /**
     * Read - Get all Likes
     * @return - An Iterable object of MyLikes full filled
     */
    @GetMapping("/my_likes")
    public Iterable<MyLikes> getMyLikes() {
        return myLikesService.getMyLikes();
    }

    /**
     * Read - Get one Like
     * @param idmy_likes The id of the SocialNetwork
     * @return An SocialNetwork object full filled
     */
    @GetMapping("/my_likes/{idmy_likes}")
    public MyLikes getMyLike(@PathVariable("idmy_likes") final Long idmy_likes) {
        Optional<MyLikes> myLikes = myLikesService.getMyLike(idmy_likes);
        if(myLikes.isPresent()) {
            return myLikes.get();
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete a Like
     * @param idartist - The id of the artist
     * @param idmusic - The id of the music
     */
    @DeleteMapping("/delete_my_like/{idartist}/{idmusic}")
    public void deleteMyLike(@PathVariable("idartist") final Long idartist, @PathVariable("idmusic") final Long idmusic) {
        myLikesService.deleteMyLike(idartist, idmusic);
    }

    @GetMapping("/my_likes_by_idartist/{idartist}")
    public Iterable<MyLikes> getMyLikesByIdArtist(@PathVariable("idartist") final Long idartist){
        return myLikesService.getMyLikesRepository().findByIdartist(idartist);
    }
}
