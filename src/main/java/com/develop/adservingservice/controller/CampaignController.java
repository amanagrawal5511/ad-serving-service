package com.develop.adservingservice.controller;

import com.develop.adservingservice.dao.AdvertiserRepository;
import com.develop.adservingservice.dao.CampaignRepository;
import com.develop.adservingservice.entity.AdvertiserEntity;
import com.develop.adservingservice.entity.CampaignEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CampaignController {
    @Autowired
    private CampaignRepository campaignRepo;

    @Autowired
    private AdvertiserRepository advertiserRepo;

    @PostMapping("api/registerCampaign")
    public CampaignEntity createCampaign(@RequestBody CampaignEntity campaign, @RequestParam(name = "advertiser_id") Long advertiserId){
        // Check if the advertiser exists
        AdvertiserEntity advertiser = advertiserRepo.findById(advertiserId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid advertiser ID"));
        campaign.setAdvertiser(advertiser);

        campaignRepo.save(campaign);
        return campaign;
    }

    @GetMapping("api/campaigns")
    public Iterable<CampaignEntity> getCampaings() {
        return campaignRepo.findAll();
    }
}
