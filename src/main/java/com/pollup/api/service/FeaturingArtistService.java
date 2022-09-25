package com.pollup.api.service;

import com.pollup.api.model.FeaturingArtist;
import com.pollup.api.repository.FeaturingArtistRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class FeaturingArtistService {

    @Autowired
    private FeaturingArtistRepository featuringArtistRepository;

    public Optional<FeaturingArtist> getFeaturing(final Long idfeaturing){
        return featuringArtistRepository.findById(idfeaturing);
    }

    public Iterable<FeaturingArtist> getFeaturings() {
        return featuringArtistRepository.findAll();
    }

    public void deleteFeaturing(final Long idartist, final Long idmusic) {
        featuringArtistRepository.deleteByIdartistAndIdmusic(idartist, idmusic);
    }

    public FeaturingArtist saveFeaturing(FeaturingArtist featuringArtist) {
        FeaturingArtist savedFeaturing = featuringArtistRepository.save(featuringArtist);
        return savedFeaturing;
    }
}
