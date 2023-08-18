package com.develop.adservingservice.controller;

import com.develop.adservingservice.entity.AdEntity;
import com.develop.adservingservice.services.AdService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AdController {

    @Autowired
    private AdService adService;

    @PostMapping("/registerAd")
    public AdEntity createAd(@RequestBody AdEntity ad, @RequestParam(name = "campaign_id") Long campaignId){
        return adService.createAd(ad, campaignId);
    }
    @GetMapping("/ads")
    public Iterable<AdEntity> getAds(){
        return adService.getAllAd();
    }
}
