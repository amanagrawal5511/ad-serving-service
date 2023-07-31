package com.develop.adservingservice.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.develop.adservingservice.entity.PublisherEntity;

@Repository
public interface PublisherRepository extends CrudRepository<PublisherEntity,Long> {

    
}
