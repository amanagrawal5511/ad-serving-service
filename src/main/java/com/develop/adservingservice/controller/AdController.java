package com.develop.adservingservice.controller;

import com.develop.adservingservice.Repository.AdRepository;
import com.develop.adservingservice.Repository.CampaignRepository;
import com.develop.adservingservice.entity.AdEntity;
import com.develop.adservingservice.entity.CampaignEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdController {
    @Autowired
    private AdRepository adRepo;
    @Autowired
    private CampaignRepository campaignRepo;
    @PostMapping("api/registerAd")
    public AdEntity createAd(@RequestBody AdEntity ad, @RequestParam(name = "campaign_id") Long campaignId){
        // Check if the advertiser exists
        CampaignEntity campaign = campaignRepo.findById(campaignId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid campaign ID"));
        ad.setCampaign(campaign);
        adRepo.save(ad);
        return ad;
    }
    @GetMapping("api/ads")
    public Iterable<AdEntity> getAds(){
        return adRepo.findAll();
    }
}
