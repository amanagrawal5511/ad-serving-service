package com.develop.adservingservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develop.adservingservice.Repository.AdBannerRepository;
import com.develop.adservingservice.Repository.ImpRepository;
import com.develop.adservingservice.entity.AdBannerEntity;
import com.develop.adservingservice.entity.ImpEntity;

@Service
public class AdBannerService {
    
    @Autowired
    private ImpRepository impRepo;

    @Autowired
    private AdBannerRepository adBannerRepo;

    public AdBannerEntity createAdBanner(AdBannerEntity adBanner, Long impId){
        ImpEntity imp = impRepo.findById(impId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Impression Id"));

        adBanner.setImpression(imp);

        return adBannerRepo.save(adBanner);
    }
}
