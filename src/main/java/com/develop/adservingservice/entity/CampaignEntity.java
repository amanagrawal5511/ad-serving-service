package com.develop.adservingservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "campaign")
public class CampaignEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String status;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "advertiser_id")
    private AdvertiserEntity advertiser;

    public List<AdEntity> getAds() {
        return ads;
    }

    public void setAds(List<AdEntity> ads) {
        this.ads = ads;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "campaign")
    private List<AdEntity> ads;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AdvertiserEntity getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(AdvertiserEntity advertiser) {
        this.advertiser = advertiser;
    }
}
