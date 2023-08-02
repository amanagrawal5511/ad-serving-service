package com.develop.adservingservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="adbanner")
public class AdBannerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double height;
    private double width;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "impression_id")
    private ImpEntity impression;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public ImpEntity getImpression() {
        return impression;
    }

    public void setImpression(ImpEntity impression) {
        this.impression = impression;
    }
     
}

