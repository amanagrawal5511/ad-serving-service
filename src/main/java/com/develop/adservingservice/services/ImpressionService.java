package com.develop.adservingservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develop.adservingservice.Repository.AdRequestRepository;
import com.develop.adservingservice.Repository.ImpRepository;
import com.develop.adservingservice.entity.AdRequestEntity;
import com.develop.adservingservice.entity.ImpEntity;

@Service
public class ImpressionService {
    
    @Autowired
    private ImpRepository impRepo;

    @Autowired
    private AdRequestRepository adRequestRepo;

    public ImpEntity createImpression(ImpEntity imp, Long adrequestId){
        AdRequestEntity adRequest = adRequestRepo.findById(adrequestId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid AdRequest Id"));

        imp.setAdrequest(adRequest);
        
        return impRepo.save(imp);
    }

    public Iterable<ImpEntity> getAllImpresions(){
        return impRepo.findAll();
    }
}
