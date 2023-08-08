package com.develop.adservingservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develop.adservingservice.Repository.AdRepository;
import com.develop.adservingservice.Repository.CampaignRepository;
import com.develop.adservingservice.entity.AdEntity;
import com.develop.adservingservice.entity.CampaignEntity;

@Service
public class AdService {
    
    @Autowired
    private CampaignRepository campaignRepo;

    @Autowired
    private AdRepository adRepo;

    public AdEntity createAd(AdEntity ad, Long camapignId){
        
        CampaignEntity campaing = campaignRepo.findById(camapignId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid campaign Id"));
        ad.setCampaign(campaing);
        return adRepo.save(ad);
    }

    public Iterable<AdEntity> getAllAd(){
        return adRepo.findAll();
    }
}
