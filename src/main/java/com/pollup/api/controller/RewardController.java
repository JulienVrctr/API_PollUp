package com.pollup.api.controller;

import com.pollup.api.model.Reward;
import com.pollup.api.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class RewardController {

    @Autowired
    private RewardService rewardService;

    /**
     * Create - Add a new Reward
     * @param reward An object Reward
     * @return The Reward object saved
     */
    @PostMapping("/add_reward")
    public Reward createReward(@RequestBody Reward reward) {
        return rewardService.saveReward(reward);
    }

    /**
     * Read - Get all rewards
     * @return - An Iterable object of Reward full filled
     */
    @GetMapping("/rewards")
    public Iterable<Reward> getRewards() {
        return rewardService.getRewards();
    }

    /**
     * Read - Get one Reward
     * @param idreward The id of the Reward
     * @return An Reward object full filled
     */
    @GetMapping("/reward/{idreward}")
    public Reward getReward(@PathVariable("idreward") final Long idreward) {
        Optional<Reward> reward = rewardService.getReward(idreward);
        if(reward.isPresent()) {
            return reward.get();
        } else {
            return null;
        }
    }

    /**
     * Update - Update an existing Reward
     * @param idreward - The id of the Reward to update
     * @param reward - The Reward object updated
     * @return
     */
    @PutMapping("/update_reward/{idreward}")
    public Reward updateReward(@PathVariable("idreward") final Long idreward, @RequestBody Reward reward) {
        Optional<Reward> e = rewardService.getReward(idreward);
        if(e.isPresent()) {
            Reward currentReward = e.get();

            String libelle = reward.getLibelle();
            if(libelle != null) {
                currentReward.setLibelle(libelle);
            }
            String image = reward.getImage();
            if(image != null) {
                currentReward.setImage(image);
            }

            rewardService.saveReward(currentReward);
            return currentReward;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete a Reward
     * @param idreward - The id of the Reward to delete
     */
    @DeleteMapping("/delete_reward/{idreward}")
    public void deleteReward(@PathVariable("idreward") final Long idreward) {
        rewardService.deleteReward(idreward);
    }
}
