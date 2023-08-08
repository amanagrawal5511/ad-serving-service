package com.develop.adservingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.develop.adservingservice.entity.AdRequestEntity;
import com.develop.adservingservice.services.AdRequestResponseService;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/api")
public class AdRequestResponse {

    @Autowired
    private AdRequestResponseService adRequestResponse;

    @PostMapping("/adRequest")
    public ObjectNode serveAdRequest(@RequestBody AdRequestEntity adrequest){
        return adRequestResponse.serveAdRequest(adrequest);
    }

    @GetMapping("/winnotice")
    public ObjectNode getWinNotice(@RequestParam(name="Id") Long Id){
        return adRequestResponse.getWinNotice(Id);
    }

}

// adRequest Format

// {
//         "id": 1,
//         "aucctionType": 1,
//         "impression": [
//             {
//                 "id": 1,
//                 "bidFloor": 1.12,
//                 "adServedId": null,
//                 "adBanner": {
//                     "id": 1,
//                     "height": 0.5,
//                     "width": 10.0
//                 }
//             },
//             {
//                 "id": 2,
//                 "bidFloor": 2.12,
//                 "adServedId": null,
//                 "adBanner": {
//                     "id": 2,
//                     "height": 50.0,
//                     "width": 110.0
//                 }
//             }
//         ],
//         "currencyType": [
//             "EUR",
//             "USD"
//         ]
// }

// adResponse Format

// {
//     "id": 1,
//     "currenyType": [
//         "EUR",
//         "USD"
//     ],
//     "seatbid": [
//         {
//             "bids": [
//                 {
//                     "impid": 1,
//                     "price": 1.12,
//                     "nurl": "localhost:8080/api/winnotice?Id=1"
//                 },
//                 {
//                     "impid": 2,
//                     "price": 2.12,
//                     "nurl": "localhost:8080/api/winnotice?Id=1",
//                     "cid": 1
//                 }
//             ]
//         }
//     ]
// }