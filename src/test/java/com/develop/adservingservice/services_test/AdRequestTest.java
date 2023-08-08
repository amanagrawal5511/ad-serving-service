package com.develop.adservingservice.services_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.develop.adservingservice.Repository.AdRequestRepository;
import com.develop.adservingservice.entity.AdBannerEntity;
import com.develop.adservingservice.entity.AdRequestEntity;
import com.develop.adservingservice.entity.ImpEntity;
import com.develop.adservingservice.services.AdRequestService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

public class AdRequestTest {

    @InjectMocks
    private AdRequestService adRequestService;

    @Mock
    private AdRequestRepository adRequestRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAdRequest() {
        AdRequestEntity adRequest = createSampleAdRequest();

        Mockito.when(adRequestRepo.save(any(AdRequestEntity.class))).thenReturn(adRequest);

        AdRequestEntity result = adRequestService.createAdRequest(adRequest);

        assertEquals(adRequest, result);
        Mockito.verify(adRequestRepo, Mockito.times(1)).save(any(AdRequestEntity.class));
    }

    @Test
    public void testGetAllAdRequest() {
        // Prepare a list of AdRequestEntity instances
        List<AdRequestEntity> adRequestList = createSampleAdRequestList();

        Mockito.when(adRequestRepo.findAll()).thenReturn(adRequestList);

        Iterable<AdRequestEntity> result = adRequestService.getAllAdRequest();

        assertEquals(adRequestList, result);
        Mockito.verify(adRequestRepo, Mockito.times(1)).findAll();
    }

    // Helper method to create a sample AdRequestEntity
    private AdRequestEntity createSampleAdRequest() {
        AdRequestEntity adRequest = new AdRequestEntity();
        adRequest.setId(1L);
        adRequest.setAucctionType(3);

        List<ImpEntity> impressions = new ArrayList<>();

        // Impression 1
        ImpEntity impression1 = new ImpEntity();
        impression1.setId(3L);

        // Create AdBannerEntity for Impression 1
        AdBannerEntity adBanner1 = new AdBannerEntity();
        adBanner1.setId(3L);
        adBanner1.setHeight(600.0);
        adBanner1.setWidth(110.0);

        // Set other properties for impression1

        impression1.setAdBanner(adBanner1);
        impression1.setBidFloor(4.12);
        impression1.setAdServedId(null);

        impressions.add(impression1);

        // Impression 2
        ImpEntity impression2 = new ImpEntity();
        impression2.setId(4L);

        // Create AdBannerEntity for Impression 2
        AdBannerEntity adBanner2 = new AdBannerEntity();
        adBanner2.setId(5L);
        adBanner2.setHeight(60.0);
        adBanner2.setWidth(10.0);

        // Set other properties for impression2

        impression2.setAdBanner(adBanner2);
        impression2.setBidFloor(5.12);
        impression2.setAdServedId(null);

        impressions.add(impression2);

        adRequest.setImpression(impressions);

        List<String> currencyType = new ArrayList<>();
        currencyType.add("EUR");
        currencyType.add("USD");

        adRequest.setCurrencyType(currencyType);

        return adRequest;
    }

    // Helper method to create a list of sample AdRequestEntity instances
    private List<AdRequestEntity> createSampleAdRequestList() {
        List<AdRequestEntity> adRequestList = new ArrayList<>();

        AdRequestEntity adRequest1 = createSampleAdRequest();
        adRequestList.add(adRequest1);

        return adRequestList;
    }

}
