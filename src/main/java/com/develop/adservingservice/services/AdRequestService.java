package com.develop.adservingservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develop.adservingservice.Repository.AdRequestRepository;
import com.develop.adservingservice.entity.AdRequestEntity;

@Service
public class AdRequestService {
    
    @Autowired
    private AdRequestRepository adRequestRepo;

    public AdRequestEntity createAdRequest(AdRequestEntity adRequest){
        return adRequestRepo.save(adRequest);
    }

    public Iterable<AdRequestEntity> getAllAdRequest(){
        return adRequestRepo.findAll();
    }
}
