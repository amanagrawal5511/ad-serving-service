package com.develop.adservingservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "adrequest")
public class AdRequestEntity {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
	private Integer auctionType;
	private List <String> currencyTypes;

    @JsonManagedReference
    @OneToMany(mappedBy = "adrequest")
    private List<ImpEntity> impression;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getAucctionType() {
        return auctionType;
    }
    public void setAucctionType(Integer aucctionType) {
        this.auctionType = aucctionType;
    }
    public List<String> getCurrencyType() {
        return currencyTypes;
    }
    public void setCurrencyType(List<String> currencyType) {
        this.currencyTypes = currencyType;
    }
    public List<ImpEntity> getImpression() {
        return impression;
    }
    public void setImpression(List<ImpEntity> impression) {
        this.impression = impression;
    }



}
