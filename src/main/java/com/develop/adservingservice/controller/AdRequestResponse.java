package com.develop.adservingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.develop.adservingservice.Repository.BannerRepository;
import com.develop.adservingservice.entity.BannerEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class AdRequestResponse {

    @Autowired
    private BannerRepository BannerRepo;
    
    @PostMapping("api/adRequest")
    public ObjectNode serveAdRequest(@RequestBody ObjectNode adRequest){

        // Access the "imp" array from the "adRequest" ObjectNode
        ArrayNode impCur = (ArrayNode) adRequest.get("cur");
        ArrayNode impArray = (ArrayNode) adRequest.get("imp");

        // Now you can convert the ArrayNode to a Java array of ObjectNode
        ObjectNode[] impressions = new ObjectNode[impArray.size()];
        for (int i = 0; i < impArray.size(); i++) {
            impressions[i] = (ObjectNode) impArray.get(i);
        }

        ObjectNode[] cur = new ObjectNode[impCur.size()];

        ObjectNode publisherBanner = (ObjectNode) impressions[0].get("banner");
        JsonNode heightNode = publisherBanner.get("height");
        JsonNode widthNode = publisherBanner.get("width");

        // Getting details of all banners available on advertiser side
        List<BannerEntity> bannerList = (List<BannerEntity>) BannerRepo.findAll();

        // Convert the list to an array of BannerEntity
        BannerEntity[] banners = bannerList.toArray(new BannerEntity[bannerList.size()]);

        BannerEntity bidbanner = banners[0];

        for(BannerEntity banner: banners){
            if(banner.getHeight()< heightNode.asInt() && banner.getWidth()< widthNode.asInt()){
                bidbanner = banner;
                break;
            }
        }


		ObjectNode adResponse = JsonNodeFactory.instance.objectNode();
	    adResponse.put("id", bidbanner.getAd().getId());
 	    adResponse.put("bidid", bidbanner.getAd().getCampaign().getAdvertiser().getId());
 	    ArrayNode tags = JsonNodeFactory.instance.arrayNode();
        for (int i = 0; i < impCur.size(); i++) {
            cur[i] = (ObjectNode) impCur.get(i);
            tags.add(cur[i].asText());
        }
	    adResponse.set("cur", tags);


        // Creating Bid
        ObjectNode bid = JsonNodeFactory.instance.objectNode();
	   // bid.put("impid", impressions[0].get("id").asLong());
	    bid.put("price", impressions[0].get("bidfloor").asDouble());
	    bid.put("nurl", "localhost:8080/api/winnotice?Id="+(bidbanner.getAd().getId()));
	    bid.put("cid", bidbanner.getAd().getCampaign().getId());

        

        return adResponse;
    }


    @GetMapping("api/winnotice")
	public ObjectNode getWinNotice(@RequestParam(name = "Id") Long Id)
	{
		
		ObjectNode adResponse = JsonNodeFactory.instance.objectNode();
		adResponse.put("id", Id);

        // Ad Respose format
 		// {
		// 	"id": "",
		// 	"users": "",
		// 	"cur": "",
		// 	"seatbid":
		// [
		// 	{
		// 		"bids": 
		// 		[

		// 		]
		// 	}
		// ]

		// }

        //      Not passing the users for now	
        // 		ObjectMapper objectMapper = new ObjectMapper();
        // 		JsonNode jsonNode = objectMapper.valueToTree(ad.getUsers());	
        // 		objectNode.put("users", jsonNode);
            
        //      Not passing currency for now
        // 		ArrayNode tags = JsonNodeFactory.instance.arrayNode();
        // 	    for (String str:ad.getCur())
        // 	    {
        // 	    	tags.add(str);
        // 	    }
        // 	    objectNode.set("cur", tags);


        // 	    ArrayNode bids = JsonNodeFactory.instance.arrayNode();
        // 	    for(Imp imp : array)
        // 	    {
        // 	    	ObjectNode bid = JsonNodeFactory.instance.objectNode();
        // 	    	bid.put("impid", imp.getId());
        // 	    	bid.put("price", imp.getBidfloor());
        // 	    	bid.put("cid", imp.getAd().getCampaign().getId());
        // 	    	bids.add(bid);
        // 	    }
                
        // 	    ArrayNode seatbids = JsonNodeFactory.instance.arrayNode();
        // 	    ObjectNode aux_bids = JsonNodeFactory.instance.objectNode();
        // 	    aux_bids.put("bids", bids);
        // 	    seatbids.add(aux_bids);
        // 	    objectNode.put("seatbid", seatbids);
		
	    return adResponse;
	}

}