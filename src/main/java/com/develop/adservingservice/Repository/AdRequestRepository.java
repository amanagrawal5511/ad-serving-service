package com.develop.adservingservice.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.develop.adservingservice.entity.AdRequestEntity;

@Repository
public interface AdRequestRepository extends CrudRepository<AdRequestEntity,Long> {

    
}
