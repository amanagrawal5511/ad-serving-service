package com.develop.adservingservice.controller;

import com.develop.adservingservice.dao.AdRepository;
import com.develop.adservingservice.dao.BannerRepository;
import com.develop.adservingservice.dao.CampaignRepository;
import com.develop.adservingservice.entity.AdEntity;
import com.develop.adservingservice.entity.BannerEntity;
import com.develop.adservingservice.entity.CampaignEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BannerController {
    @Autowired
    private BannerRepository bannerRepo;
    @Autowired
    private AdRepository adRepo;
    @PostMapping("api/registerBanner")
    public BannerEntity createAd(@RequestBody BannerEntity banner, @RequestParam(name = "ad_id") Long adId){
        // Check if the advertiser exists
        AdEntity ad = adRepo.findById(adId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid campaign ID"));
        banner.setAd(ad);
        bannerRepo.save(banner);
        return banner;
    }
    @GetMapping("api/banners")
    public Iterable<BannerEntity> getBanners(){
        return bannerRepo.findAll();
    }
}
