package com.develop.adservingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.develop.adservingservice.Repository.AdBannerRepository;
import com.develop.adservingservice.Repository.AdRepository;
import com.develop.adservingservice.Repository.AdRequestRepository;
import com.develop.adservingservice.Repository.ImpRepository;
import com.develop.adservingservice.entity.AdEntity;
import com.develop.adservingservice.entity.AdRequestEntity;
import com.develop.adservingservice.entity.ImpEntity;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class AdRequestResponse {

    @Autowired
    private AdBannerRepository AdBannerRepo;

    @Autowired
    private ImpRepository ImpRepo;

    @Autowired
    private AdRequestRepository AdRequestRepo;

    @Autowired
    private AdRepository AdRepo;

    @PostMapping("api/adRequest")
    public ObjectNode serveAdRequest(@RequestParam(name ="adrequest_id") Long adrequestId ){

        AdRequestEntity adrequest = AdRequestRepo.findById(adrequestId)
            .orElseThrow(() -> new RuntimeException("AdRequest not found with id: "));
        System.out.println(adrequest);

       // Getting Impressions from the AdRequest(Body)
		List<ImpEntity> imps = adrequest.getImpression();
		ImpEntity[] array = imps.toArray(new ImpEntity[imps.size()]);
		for (int i=0; i<imps.size(); i++)
		{
			ImpEntity imp = array[i];
			// Getting Banners from the Imp(Body) & saving them.
			AdBannerRepo.save(imp.getAdBanner());
			// Saving Imps
			ImpRepo.save(imp);
		}

        AdRequestRepo.save(adrequest);
        
        
        List<AdEntity> ads = (List<AdEntity>) AdRepo.findAll();

        for(ImpEntity imp: array){

            for(AdEntity ad:ads){
                if(ad.getAdHeight()<imp.getAdBanner().getHeight() && ad.getAdWidth()<imp.getAdBanner().getWidth()){
                    imp.setAdServedId(ad.getId());
                    break;
                }
            }
        }

        ObjectNode adResponse = JsonNodeFactory.instance.objectNode();
        adResponse.put("id",adrequest.getId());
        
        ArrayNode tags = JsonNodeFactory.instance.arrayNode();
        for(String str:adrequest.getCurrencyType()){
            tags.add(str);
        }
        adResponse.set("currenyType", tags);

        ArrayNode bids = JsonNodeFactory.instance.arrayNode();
        for(ImpEntity imp: array){

            ObjectNode bid = JsonNodeFactory.instance.objectNode();
            bid.put("impid", imp.getId());
            bid.put("price",imp.getBidFloor());
            bid.put("nurl", "localhost:8080/api/winnotice?Id="+(adrequest.getId()));


            AdEntity ad = AdRepo.findById(imp.getAdServedId()) 
            .orElseThrow(() -> new RuntimeException("Ad not found with id: "));

            bid.put("cid", ad.getCampaign().getId());
            bids.add(bid);
        }

        ArrayNode seatbids = JsonNodeFactory.instance.arrayNode();
	    ObjectNode aux_bids = JsonNodeFactory.instance.objectNode();
        aux_bids.set("bids",bids);
	    seatbids.add(aux_bids);
	    adResponse.set("seatbid", seatbids);
        
        return adResponse;
    }

    @GetMapping("api/winnotice")
    public ObjectNode getWinNotice(@RequestParam(name="Id") Long Id){

        AdRequestEntity adRequest = AdRequestRepo.findById(Id) 
            .orElseThrow(() -> new IllegalArgumentException("Invalid AdRequest Id"));

        // Getting Impressions from the AdRequest(Body)
		List<ImpEntity> imps = adRequest.getImpression();
		ImpEntity[] array = imps.toArray(new ImpEntity[imps.size()]);

        ObjectNode winObject = JsonNodeFactory.instance.objectNode();
        winObject.put("Id",Id);

        ArrayNode tags = JsonNodeFactory.instance.arrayNode();
        for(String str: adRequest.getCurrencyType()){
            tags.add(str);
        }
        winObject.set("currenyType", tags);

        ArrayNode bids = JsonNodeFactory.instance.arrayNode();
        for(ImpEntity imp: array){

            ObjectNode bid = JsonNodeFactory.instance.objectNode();
            bid.put("impid", imp.getId());
            bid.put("price",imp.getBidFloor());

            AdEntity ad = AdRepo.findById(imp.getAdServedId()) 
            .orElseThrow(() -> new RuntimeException("Ad not found with id: "));

            bid.put("cid", ad.getCampaign().getId());
            bids.add(bid);
        }

        ArrayNode seatbids = JsonNodeFactory.instance.arrayNode();
	    ObjectNode aux_bids = JsonNodeFactory.instance.objectNode();
        aux_bids.set("bids",bids);
	    seatbids.add(aux_bids);
	    winObject.set("seatbid", seatbids);

        return winObject;

    }

}

