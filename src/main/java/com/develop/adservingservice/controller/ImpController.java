package com.develop.adservingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.develop.adservingservice.Repository.AdRequestRepository;
import com.develop.adservingservice.Repository.ImpRepository;
import com.develop.adservingservice.entity.AdRequestEntity;
import com.develop.adservingservice.entity.ImpEntity;

@RestController
public class ImpController {
    
    @Autowired
    private AdRequestRepository adRequestRepo;

    @Autowired
    private ImpRepository ImpRepo;

    @PostMapping("api/registerImpression")
    public ImpEntity createImpression(@RequestBody ImpEntity impression, @RequestParam(name = "adrequest_id") Long adrequestId){
        // Check if AdRequest Exist
        AdRequestEntity adRequest = adRequestRepo.findById(adrequestId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid AdRequest"));

        impression.setAdrequest(adRequest);

        ImpRepo.save(impression);
        return impression;
    }
}
