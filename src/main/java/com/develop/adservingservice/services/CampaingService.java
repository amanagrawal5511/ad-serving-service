package com.develop.adservingservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develop.adservingservice.Repository.AdvertiserRepository;
import com.develop.adservingservice.Repository.CampaignRepository;
import com.develop.adservingservice.entity.AdvertiserEntity;
import com.develop.adservingservice.entity.CampaignEntity;

@Service
public class CampaingService {
    
    @Autowired
    private CampaignRepository campaignRepo;

    @Autowired AdvertiserRepository advertiserRepo;

    public CampaignEntity createCampaing(CampaignEntity campaign, Long advertiserId){
        // Check if the advertiser exists
        AdvertiserEntity advertiser = advertiserRepo.findById(advertiserId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid advertiser ID"));
        campaign.setAdvertiser(advertiser);

        return campaignRepo.save(campaign);
    }

    public Iterable<CampaignEntity> getAllCampaing(){
        return campaignRepo.findAll();
    }
}
