package com.develop.adservingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.develop.adservingservice.Repository.AdBannerRepository;
import com.develop.adservingservice.Repository.ImpRepository;
import com.develop.adservingservice.entity.AdBannerEntity;
import com.develop.adservingservice.entity.ImpEntity;

@RestController
public class AdBannerController {
    
    @Autowired
    private ImpRepository ImpRepo;

    @Autowired
    private AdBannerRepository AdBannerRepo;

    @PostMapping("api/createAdBanner")
    public AdBannerEntity createAdBanner(@RequestBody AdBannerEntity adBanner, @RequestParam(name = "impression_id") Long impressionId){
        
        ImpEntity imp = ImpRepo.findById(impressionId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Impression Id"));

        adBanner.setImpression(imp);

        AdBannerRepo.save(adBanner);
        
        return adBanner;
    }
}
