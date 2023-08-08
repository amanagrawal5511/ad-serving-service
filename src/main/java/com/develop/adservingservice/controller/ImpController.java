package com.develop.adservingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.develop.adservingservice.entity.ImpEntity;
import com.develop.adservingservice.services.ImpressionService;

@RestController
@RequestMapping("/api")
public class ImpController {
    
    @Autowired
    private ImpressionService impService;

    @PostMapping("/registerImpression")
    public ImpEntity createImpression(@RequestBody ImpEntity impression, @RequestParam(name = "adrequest_id") Long adrequestId){
        return impService.createImpression(impression, adrequestId);
    }

    @GetMapping("/impressions")
    public Iterable<ImpEntity> getAllImpression(){
        return impService.getAllImpresions();
    }
}
