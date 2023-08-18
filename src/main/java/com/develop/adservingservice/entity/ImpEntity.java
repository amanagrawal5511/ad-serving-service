package com.develop.adservingservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name="impression")
public class ImpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double bidFloor;
    private Long adServedId;
    private double bidAmount;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "adrequest_id")
    private AdRequestEntity adrequest;

    @JsonManagedReference
    @OneToOne(mappedBy = "impression")
    private AdBannerEntity adBanner;

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public double getBidFloor() {
        return bidFloor;
    }

    public void setBidFloor(double bidFloor) {
        this.bidFloor = bidFloor;
    }

    public AdRequestEntity getAdrequest() {
        return adrequest;
    }

    public void setAdrequest(AdRequestEntity adrequest) {
        this.adrequest = adrequest;
    }

    public AdBannerEntity getAdBanner() {
        return adBanner;
    }

    public void setAdBanner(AdBannerEntity adBanner) {
        this.adBanner = adBanner;
    }

    public Long getAdServedId() {
        return adServedId;
    }

    public void setAdServedId(Long adServedId) {
        this.adServedId = adServedId;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

       
}
