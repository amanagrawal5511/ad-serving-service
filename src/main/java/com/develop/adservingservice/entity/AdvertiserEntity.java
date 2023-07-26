package com.develop.adservingservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "advertiser")
public class AdvertiserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;

    @JsonManagedReference
    @OneToMany(mappedBy = "advertiser")
    private List<CampaingEntity> campaings;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<CampaingEntity> getCampaings() {
        return campaings;
    }

    public void setCampaings(List<CampaingEntity> campaings) {
        this.campaings = campaings;
    }
}
