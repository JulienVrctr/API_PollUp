package com.pollup.api.service;

import com.pollup.api.model.Reward;
import com.pollup.api.repository.RewardRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    public Optional<Reward> getReward(final Long idreward){
        return rewardRepository.findById(idreward);
    }

    public Iterable<Reward> getRewards() {
        return rewardRepository.findAll();
    }

    public void deleteReward(final Long idreward) {
        rewardRepository.deleteById(idreward);
    }

    public Reward saveReward(Reward reward) {
        Reward savedReward = rewardRepository.save(reward);
        return savedReward;
    }
}
