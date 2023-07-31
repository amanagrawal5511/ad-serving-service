package com.develop.adservingservice.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.develop.adservingservice.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {

    
}
