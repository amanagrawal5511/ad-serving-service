package com.develop.adservingservice.controller;

import com.develop.adservingservice.dao.AdvertiserRepository;
import com.develop.adservingservice.dao.CampaingRepository;
import com.develop.adservingservice.entity.AdvertiserEntity;
import com.develop.adservingservice.entity.CampaingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CampaingController {

    @Autowired
    private CampaingRepository campaingRepo;

    @Autowired
    private AdvertiserRepository advertiserRepo;

    @PostMapping("api/registerCampaing")
    public CampaingEntity createCampaing(@RequestBody CampaingEntity campaing,@RequestParam(name = "advertiser_id") Long advertiserId){
        // Check if the advertiser exists
        AdvertiserEntity advertiser = advertiserRepo.findById(advertiserId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid advertiser ID"));
        //System.out.println(advertiser);
        campaing.setAdvertiser(advertiser);

        campaingRepo.save(campaing);
        return campaing;
    }

    @GetMapping("api/campaings")
    public Iterable<CampaingEntity> getCampaings() {
        return campaingRepo.findAll();
    }
}
