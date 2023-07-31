package com.develop.adservingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.develop.adservingservice.dao.UserRepository;
import com.develop.adservingservice.entity.UserEntity;

@RestController
public class UserController {

    @Autowired
    private UserRepository UserRepo;

    @PostMapping("api/registerUser")
    public UserEntity createUser(@RequestBody UserEntity user){
       // System.out.println(user);
        UserRepo.save(user);
        return user;
    }

    @GetMapping("api/users")
    public Iterable<UserEntity> getUsers(){
       return UserRepo.findAll();
    }

}
