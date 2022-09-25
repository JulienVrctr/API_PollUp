package com.pollup.api.repository;

import com.pollup.api.model.SocialNetwork;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialNetworkRepository extends CrudRepository<SocialNetwork, Long> {
}
