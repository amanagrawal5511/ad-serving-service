package com.develop.adservingservice.entity;

import org.springframework.boot.context.properties.bind.DefaultValue;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="imp")
public class ImpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int maxWidth;
    private int maxHeight;
    private double bidFloor;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private PublisherEntity publisher;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public double getBidFloor() {
        return bidFloor;
    }

    public void setBidFloor(double bidFloor) {
        this.bidFloor = bidFloor;
    }
    public PublisherEntity getPublisher() {
        return publisher;
    }
    public void setPublisher(PublisherEntity publisher) {
        this.publisher = publisher;
    }
}
