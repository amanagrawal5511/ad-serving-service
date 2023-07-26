package com.develop.adservingservice.dao;

import com.develop.adservingservice.entity.CampaingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaingRepository extends CrudRepository<CampaingEntity, Long> {

}
