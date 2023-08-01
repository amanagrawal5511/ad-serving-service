package com.develop.adservingservice.Repository;

import com.develop.adservingservice.entity.BannerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends CrudRepository<BannerEntity, Long> {
}
