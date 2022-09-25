package com.pollup.api.repository;

import com.pollup.api.model.Reward;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface RewardRepository extends CrudRepository<Reward, Long> {
}
