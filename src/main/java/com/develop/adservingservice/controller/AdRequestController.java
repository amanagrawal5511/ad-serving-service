package com.develop.adservingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.adservingservice.entity.AdRequestEntity;
import com.develop.adservingservice.services.AdRequestService;


@RestController
@RequestMapping("/api")
public class AdRequestController {

    @Autowired
    private AdRequestService adRequestService;

    @PostMapping("/registerAdRequest")
    public AdRequestEntity createAdRequest(@RequestBody AdRequestEntity adrequest){
        return adRequestService.createAdRequest(adrequest);
    }

    @GetMapping("/adRequests")
    public Iterable<AdRequestEntity> getAdRequests(){
        return adRequestService.getAllAdRequest();
    }
}
