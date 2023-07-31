package com.develop.adservingservice.controller;

import com.develop.adservingservice.dao.PublisherRepository;
import com.develop.adservingservice.dao.ImpRepository;
import com.develop.adservingservice.entity.PublisherEntity;
import com.develop.adservingservice.entity.ImpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ImpController {
    @Autowired
    private ImpRepository impRepo;
    @Autowired
    private PublisherRepository publisherRepo;

    @PostMapping("api/registerImp")
    private ImpEntity registerEntity(@RequestBody ImpEntity impression, @RequestParam("publisher_id") Long publisherId){
        PublisherEntity publisher = publisherRepo.findById(publisherId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid publisher ID"));
        impression.setPublisher(publisher);
        System.out.println(impression.getMaxHeight());
        System.out.println(impression.getMaxWidth());
        System.out.println(impression.getBidFloor());
        impRepo.save(impression);

        return impression;
    }
    @GetMapping("api/getImps")
    private Iterable<ImpEntity> getImpressionsOffered(){
        return impRepo.findAll();
    }

}