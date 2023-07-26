package com.develop.adservingservice.dao;

import com.develop.adservingservice.entity.AdvertiserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertiserRepository extends CrudRepository<AdvertiserEntity, Long> {
}
