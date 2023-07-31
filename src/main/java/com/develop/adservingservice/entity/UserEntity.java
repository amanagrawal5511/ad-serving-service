package com.develop.adservingservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "publisher")
public class UserEntity {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long maxHeight;
    private Long maxWidth;


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
    public Long getMaxHeight() {
        return maxHeight;
    }
    public void setMaxHeight(Long maxHeight) {
        this.maxHeight = maxHeight;
    }
    public Long getMaxWidth() {
        return maxWidth;
    }
    public void setMaxWidth(Long maxWidth) {
        this.maxWidth = maxWidth;
    }
   

   


}
