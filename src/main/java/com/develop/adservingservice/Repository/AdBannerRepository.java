package com.develop.adservingservice.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.develop.adservingservice.entity.AdBannerEntity;

@Repository
public interface AdBannerRepository extends CrudRepository<AdBannerEntity,Long> {

}
