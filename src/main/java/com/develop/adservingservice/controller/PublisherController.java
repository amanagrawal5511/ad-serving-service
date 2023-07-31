package com.develop.adservingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.develop.adservingservice.dao.PublisherRepository;
import com.develop.adservingservice.entity.PublisherEntity;

@RestController
public class PublisherController {

    @Autowired
    private PublisherRepository PublisherRepo;

    @PostMapping("api/registerPublisher")
    public PublisherEntity createUser(@RequestBody PublisherEntity user){
       // System.out.println(user);
        PublisherRepo.save(user);
        return user;
    }
    @GetMapping("api/publishers")
    public Iterable<PublisherEntity> getUsers(){
       return PublisherRepo.findAll();
    }
}
