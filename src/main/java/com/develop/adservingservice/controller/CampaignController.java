package com.develop.adservingservice.controller;

import com.develop.adservingservice.entity.CampaignEntity;
import com.develop.adservingservice.services.CampaingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CampaignController {
    @Autowired
    private CampaingService campaignService;

    @PostMapping("/registerCampaign")
    public CampaignEntity createCampaign(@RequestBody CampaignEntity campaign, @RequestParam(name = "advertiser_id") Long advertiserId){
        return campaignService.createCampaing(campaign, advertiserId);
    }

    @GetMapping("/campaigns")
    public Iterable<CampaignEntity> getCampaings() {
        return campaignService.getAllCampaing();
    }
}
