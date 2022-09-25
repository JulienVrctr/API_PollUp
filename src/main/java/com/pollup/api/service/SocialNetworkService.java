package com.pollup.api.service;

import com.pollup.api.model.SocialNetwork;
import com.pollup.api.repository.SocialNetworkRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class SocialNetworkService {

    @Autowired
    private SocialNetworkRepository socialNetworkRepository;

    public Optional<SocialNetwork> getSocialNetwork(final Long idsocial_network){
        return socialNetworkRepository.findById(idsocial_network);
    }

    public Iterable<SocialNetwork> getSocialNetworks() {
        return socialNetworkRepository.findAll();
    }

    public void deleteSocialNetwork(final Long idsocial_network) {
        socialNetworkRepository.deleteById(idsocial_network);
    }

    public SocialNetwork saveSocialNetwork(SocialNetwork social_network) {
        SocialNetwork savedSocialNetwork = socialNetworkRepository.save(social_network);
        return savedSocialNetwork;
    }
}
