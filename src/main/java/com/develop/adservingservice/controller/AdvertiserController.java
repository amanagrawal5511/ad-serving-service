package com.develop.adservingservice.controller;

import com.develop.adservingservice.Repository.AdvertiserRepository;
import com.develop.adservingservice.entity.AdvertiserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdvertiserController {
    @Autowired
    private AdvertiserRepository AdvertiserRepo;

    @PostMapping("api/registerAdvertiser")
    public AdvertiserEntity createAdvertiser(@RequestBody AdvertiserEntity advertiser){
        // AdvertiserEntity newAdvertiser = new AdvertiserEntity();

        AdvertiserRepo.save(advertiser);
        return advertiser;
    }

    @GetMapping("api/advertisers")
        public Iterable<AdvertiserEntity> getAdvertisers(){
        return AdvertiserRepo.findAll();
    }
}
