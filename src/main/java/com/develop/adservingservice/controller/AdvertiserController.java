package com.develop.adservingservice.controller;

import com.develop.adservingservice.entity.AdvertiserEntity;
import com.develop.adservingservice.services.AdvertiserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AdvertiserController {

    @Autowired
    private AdvertiserService advertiserService;

    // To Create the advertiser
    @PostMapping("/registerAdvertiser")
    public AdvertiserEntity createAdvertiser(@RequestBody AdvertiserEntity advertiser){
        return advertiserService.createAdvertiser(advertiser);
    }

    // To create the advertiser
    @GetMapping("/advertisers")
        public Iterable<AdvertiserEntity> getAdvertisers(){
        return advertiserService.getAllAdvertiser();
    }
}
