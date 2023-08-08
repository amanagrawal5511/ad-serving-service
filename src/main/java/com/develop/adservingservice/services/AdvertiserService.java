package com.develop.adservingservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develop.adservingservice.Repository.AdvertiserRepository;
import com.develop.adservingservice.entity.AdvertiserEntity;

@Service
public class AdvertiserService {
    
    @Autowired
    private AdvertiserRepository advertiserRepo;

    // Create a new advertiser
    public AdvertiserEntity createAdvertiser(AdvertiserEntity advertiser){
        return advertiserRepo.save(advertiser);
    }

    // Get all advertisers
    public Iterable<AdvertiserEntity> getAllAdvertiser(){
        return advertiserRepo.findAll();
    }
}
