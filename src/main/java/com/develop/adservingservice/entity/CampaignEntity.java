package com.develop.adservingservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "campaign")
public class CampaignEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "advertiser_id")
    private AdvertiserEntity advertiser;
    @JsonManagedReference
    @OneToMany(mappedBy = "campaign")
    private List<AdEntity> ads;
    public List<AdEntity> getAds() {
        return ads;
    }

    public void setAds(List<AdEntity> ads) {
        this.ads = ads;
    }

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
