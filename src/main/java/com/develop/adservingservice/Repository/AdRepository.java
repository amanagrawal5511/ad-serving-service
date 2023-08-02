package com.develop.adservingservice.Repository;

import com.develop.adservingservice.entity.AdEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends CrudRepository<AdEntity, Long> {
}
