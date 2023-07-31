package com.develop.adservingservice.controller;

import com.develop.adservingservice.dao.PublisherRepository;
import com.develop.adservingservice.dao.ImpRepository;
import com.develop.adservingservice.entity.AdvertiserEntity;
import com.develop.adservingservice.entity.PublisherEntity;
import com.develop.adservingservice.entity.ImpEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ImpController {
    @Autowired
    private ImpRepository impRepo;
    @Autowired
    private PublisherRepository publisherRepo;
    @PostMapping("api/registerImp")
    private ImpEntity registerEntity(@RequestBody ImpEntity impEntity, @RequestParam("publisher_id") Long publisherId){
        PublisherEntity publisher = publisherRepo.findById(publisherId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid advertiser ID"));
        impEntity.setPublisher(publisher);
        impRepo.save(impEntity);
        return impEntity;
    }
    @GetMapping("api/getImps")
    private Iterable<ImpEntity> getImpressionsOffered(){
        return impRepo.findAll();
    }

}