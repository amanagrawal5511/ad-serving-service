package com.develop.adservingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.develop.adservingservice.dao.PublisherRepository;

@RestController
public class bidRequestResponseController {
    

    @Autowired
    private PublisherRepository publisherRepo;

    

}











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
