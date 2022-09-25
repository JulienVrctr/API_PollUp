package com.pollup.api.controller;

import com.pollup.api.model.SocialNetwork;
import com.pollup.api.service.SocialNetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SocialNetworkController {

    @Autowired
    private SocialNetworkService socialNetworkService;

    /**
     * Create - Add a new SocialNetwork
     * @param social_network An object SocialNetwork
     * @return The SocialNetwork object saved
     */
    @PostMapping("/add_social_network")
    public SocialNetwork createSocialNetwork(@RequestBody SocialNetwork social_network) {
        return socialNetworkService.saveSocialNetwork(social_network);
    }

    /**
     * Read - Get all SocialNetworks
     * @return - An Iterable object of SocialNetwork full filled
     */
    @GetMapping("/social_networks")
    public Iterable<SocialNetwork> getSocialNetworks() {
        return socialNetworkService.getSocialNetworks();
    }

    /**
     * Read - Get one SocialNetwork
     * @param idsocial_network The id of the SocialNetwork
     * @return An SocialNetwork object full filled
     */
    @GetMapping("/social_network/{idsocial_network}")
    public SocialNetwork getSocialNetwork(@PathVariable("idsocial_network") final Long idsocial_network) {
        Optional<SocialNetwork> socialNetwork = socialNetworkService.getSocialNetwork(idsocial_network);
        if(socialNetwork.isPresent()) {
            return socialNetwork.get();
        } else {
            return null;
        }
    }

    /**
     * Update - Update an existing SocialNetwork
     * @param idsocial_network - The id of the SocialNetwork to update
     * @param social_network - The SocialNetwork object updated
     * @return
     */
    @PutMapping("/update_social_network/{idsocial_network}")
    public SocialNetwork udpateSocialNetwork(@PathVariable("idsocial_network") final Long idsocial_network, @RequestBody SocialNetwork social_network) {
        Optional<SocialNetwork> e = socialNetworkService.getSocialNetwork(idsocial_network);
        if(e.isPresent()) {
            SocialNetwork currentSocialNetwork = e.get();

            String name = social_network.getSocial_network();
            if(name != null) {
                currentSocialNetwork.setSocial_network(name);
            }

            String link = social_network.getLink();
            if(name != null) {
                currentSocialNetwork.setLink(link);
            }

            socialNetworkService.saveSocialNetwork(currentSocialNetwork);
            return currentSocialNetwork;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete a SocialNetwork
     * @param idsocial_network - The id of the SocialNetwork to delete
     */
    @DeleteMapping("/delete_social_network/{idsocial_network}")
    public void deleteSocialNetwork(@PathVariable("idsocial_network") final Long idsocial_network) {
        socialNetworkService.deleteSocialNetwork(idsocial_network);
    }
}
