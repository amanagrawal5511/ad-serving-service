package com.develop.adservingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.naming.ObjectNamingStrategy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.develop.adservingservice.dao.BannerRepository;
import com.develop.adservingservice.entity.BannerEntity;
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
        ArrayNode impArray = (ArrayNode) adRequest.get("imp");

        // Now you can convert the ArrayNode to a Java array of ObjectNode
        ObjectNode[] impressions = new ObjectNode[impArray.size()];
        for (int i = 0; i < impArray.size(); i++) {
            impressions[i] = (ObjectNode) impArray.get(i);
        }

        List<BannerEntity> bannerList = (List<BannerEntity>) BannerRepo.findAll();

        // Convert the list to an array of BannerEntity
        BannerEntity[] banners = bannerList.toArray(new BannerEntity[bannerList.size()]);





        return adRequest;
    }

}




// {
// 			"at" : 1,
// 			"cur" : ["EUR", "USD"],
// 			"imp" : [{
// 				"id" : 1,
// 				"bidfloor" : 0.03,
// 				"banner" : {
// 					"width" : 10.0,
// 					"height" : 0.5,
// 					"pos" : 129
// 				}
// 			}, 
// 			{
// 				"id" : 2,
// 				"bidfloor" : 0.05,
// 				"banner" : {
// 					"width" : 1.0,
// 					"height" : 0.5,
// 					"pos" : 129
// 				}
// 			}]
// 		}
        






// @RestController
// public class AdController {
// 	@Autowired
// 	private AdRepo adRepo;
	
// 	@Autowired
// 	private CampaignRepo campaignRepo;
	
// 	@Autowired
// 	private ImpRepo impRepo;
	
// 	@Autowired
// 	private BannerRepo bannerRepo;
	
// 	@Autowired
// 	private UserRepo userRepo;
	
// 	@GetMapping("api/ad")
// 	public Iterable<Ad> getAllAd()
// 	{
// 		return adRepo.findAll();
// 	}
	
// 	@PostMapping("api/ad")
// 	public ObjectNode createAd(@RequestBody Ad newAd, @RequestParam(name = "campaign_id") Long campaignId, @RequestParam(name="user_ids") List<Long> userIds)
// 	{    
// 		Campaign campaign = campaignRepo.findById(campaignId)
// 				.orElseThrow(() -> new IllegalArgumentException("Invalid campaign ID"));
		
// 		List<User> users = (List<User>) userRepo.findAllById(userIds);	

// 		// Getting Impressions from the Ad(Body)
// 		List<Imp> imps = newAd.getImp();
// 		Imp[] array = imps.toArray(new Imp[imps.size()]);
// 		for (int i=0; i<imps.size(); i++)
// 		{
// 			Imp imp = array[i];
// 			// Getting Banners from the Imp(Body) & saving them.
// 			bannerRepo.save(imp.getBanner());
// 			// Saving Imps
// 			impRepo.save(imp);
// 		}

// 		newAd.setCampaign(campaign);

// 		// Saving the Ad
// 		adRepo.save(newAd);
		
		
		
// 		for(User user:users)
// 		{
// 			boolean accepted = true;
// 			for (Imp imp : array)
// 			{
// 				if(imp.getBanner().getHeight()>user.getMax_height() || imp.getBanner().getWidth()>user.getMax_width())
// 				{
// 					accepted = false;
// 				}
// 			}

// 			if(accepted)
// 			{
// 				// If bid is win assign the Ad to the publisher(user);
// 				user.setAd(newAd);
// 				userRepo.save(user);
// 			}

// 		}
		
// 		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
// 	    objectNode.put("id", newAd.getId());
// //	    objectNode.put("bidid", newAd.getCampaign().getAdvertiser().getId());
// 	    ArrayNode tags = JsonNodeFactory.instance.arrayNode();
// 	    for (String str:newAd.getCur())
// 	    {
// 	    	tags.add(str);
// 	    }
// 	    objectNode.set("cur", tags);

// 		// {
// 		// 	"id": "",
// 		// 	"cur": [],
// 		//  "seatbid":
// 		// [
// 		// 	{
// 		// 		"bids": 
// 		// 		[

// 		// 		]
// 		// 	}
// 		// ]	
// 		// }
	   
// 		ArrayNode bids = JsonNodeFactory.instance.arrayNode();

// 	    for(Imp imp : array)
// 	    {
// 	    	ObjectNode bid = JsonNodeFactory.instance.objectNode();
// 	    	bid.put("impid", imp.getId());
// 	    	bid.put("price", imp.getBidfloor());
// 	    	bid.put("nurl", "localhost:8080/api/winnotice?Id="+(newAd.getId()));
// 	    	bid.put("cid", imp.getAd().getCampaign().getId());
// 	    	bids.add(bid);
// 	    }
	    
// 	    ArrayNode seatbids = JsonNodeFactory.instance.arrayNode();
// 	    ObjectNode aux_bids = JsonNodeFactory.instance.objectNode();
// 	    aux_bids.put("bids", bids);
// 	    seatbids.add(aux_bids);
// 	    objectNode.put("seatbid", seatbids);
	    
// 	    return objectNode;
// 	}
	
// 	@GetMapping("api/winnotice")
// 	public ObjectNode getWinNotice(@RequestParam(name = "Id") Long Id)
// 	{
// 		Ad ad = adRepo.findById(Id).orElseThrow(() -> new IllegalArgumentException("Invalid campaign ID"));
// 		List<Imp> imps = ad.getImp();
// 		Imp[] array = imps.toArray(new Imp[imps.size()]);
		
// 		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
// 		objectNode.put("id", Id);

// 		// {
// 		// 	"id": "",
// 		// 	"users": "",
// 		// 	"cur": "",
// 		// 	"seatbid":
// 		// [
// 		// 	{
// 		// 		"bids": 
// 		// 		[

// 		// 		]
// 		// 	}
// 		// ]

// 		// }
		
// 		ObjectMapper objectMapper = new ObjectMapper();
// 		JsonNode jsonNode = objectMapper.valueToTree(ad.getUsers());
		
// 		objectNode.put("users", jsonNode);
		
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
		
// 	    return objectNode;
// 	}
// }
