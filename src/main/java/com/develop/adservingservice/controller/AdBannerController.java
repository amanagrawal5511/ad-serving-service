package com.develop.adservingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.develop.adservingservice.entity.AdBannerEntity;
import com.develop.adservingservice.services.AdBannerService;

@RestController
public class AdBannerController {
    
    @Autowired
    private AdBannerService adBannerService;

    @PostMapping("api/createAdBanner")
    public AdBannerEntity createAdBanner(@RequestBody AdBannerEntity adBanner, @RequestParam(name = "impression_id") Long impressionId){
        
        return adBannerService.createAdBanner(adBanner, impressionId);
    }
}
