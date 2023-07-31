package com.develop.adservingservice.controller;

import java.util.List;

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

    // @Autowired
    // private ImpRepository ImpRepo;

    @PostMapping("api/registerPublisher")
    public PublisherEntity createUser(@RequestBody PublisherEntity publisher){
    //    List<ImpEntity> imps = publisher.getImps();
    //     // Getting Impressions
	// 	ImpEntity[] array = imps.toArray(new ImpEntity[imps.size()]);
	// 	for (int i=0; i<imps.size(); i++)
	// 	{
	// 		ImpEntity imp = array[i];
	// 		// Saving Imps
	// 		ImpRepo.save(imp);
	// 	}
        PublisherRepo.save(publisher);
        return publisher;
    }


    @GetMapping("api/publishers")
    public Iterable<PublisherEntity> getPublishers(){
       return PublisherRepo.findAll();
    }
}
