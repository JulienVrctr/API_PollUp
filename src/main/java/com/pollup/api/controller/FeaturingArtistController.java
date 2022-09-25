package com.pollup.api.controller;

import com.pollup.api.model.FeaturingArtist;
import com.pollup.api.service.FeaturingArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class FeaturingArtistController {

    @Autowired
    private FeaturingArtistService featuringArtistService;

    /**
     * Create - Add a new Featuring
     * @param featuring An object FeaturingArtist
     * @return The FeaturingArtist object saved
     */
    @PostMapping("/add_featuring")
    public FeaturingArtist createFeaturing(@RequestBody FeaturingArtist featuring) {
        return featuringArtistService.saveFeaturing(featuring);
    }

    /**
     * Read - Get all FeaturingArtist
     * @return - An Iterable object of FeaturingArtist full filled
     */
    @GetMapping("/featurings")
    public Iterable<FeaturingArtist> getFeaturings() {
        return featuringArtistService.getFeaturings();
    }

    /**
     * Read - Get one FeaturingArtist
     * @param idfeaturing The id of the FeaturingArtist
     * @return An FeaturingArtist object full filled
     */
    @GetMapping("/featuring/{idfeaturing}")
    public FeaturingArtist getFeaturing(@PathVariable("idfeaturing") final Long idfeaturing) {
        Optional<FeaturingArtist> featuringArtist = featuringArtistService.getFeaturing(idfeaturing);
        if(featuringArtist.isPresent()) {
            return featuringArtist.get();
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete a Featuring
     * @param idartist - The id of the artist
     * @param idmusic - The id of the music
     */
    @DeleteMapping("/delete_featuring/{idartist}/{idmusic}")
    public void deleteFeaturing(@PathVariable("idartist") final Long idartist, @PathVariable("idmusic") final Long idmusic) {
        featuringArtistService.deleteFeaturing(idartist, idmusic);
    }
}
