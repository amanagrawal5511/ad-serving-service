package com.develop.adservingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.develop.adservingservice.Repository.AdRequestRepository;
import com.develop.adservingservice.entity.AdRequestEntity;


@RestController
public class AdRequestController {

    @Autowired
    private AdRequestRepository AdRequestRepo;

    @PostMapping("api/registerAdRequest")
    public AdRequestEntity createUser(@RequestBody AdRequestEntity adrequest){
        AdRequestRepo.save(adrequest);
        return adrequest;
    }
}
