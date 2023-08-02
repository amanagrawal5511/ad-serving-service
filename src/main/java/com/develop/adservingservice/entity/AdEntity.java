package com.develop.adservingservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="ad")
public class AdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double adHeight;
    private double adWidth;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private CampaignEntity campaign;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CampaignEntity getCampaign() {
        return campaign;
    }

    public void setCampaign(CampaignEntity campaign) {
        this.campaign = campaign;
    }

    public double getAdHeight() {
        return adHeight;
    }

    public void setAdHeight(double adHeight) {
        this.adHeight = adHeight;
    }

    public double getAdWidth() {
        return adWidth;
    }

    public void setAdWidth(double adWidth) {
        this.adWidth = adWidth;
    }

    

    
}
