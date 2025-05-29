package com.plantsim.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Plant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String species;
    private Integer age;
    private String soilType;
    
    // Constructor vacío
    public Plant() {
    }
    
    // Constructor con parámetros
    public Plant(Long id, String name, String species, Integer age, String soilType) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.age = age;
        this.soilType = soilType;
    }
    
    // Getters y setters
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
    
    public String getSpecies() {
        return species;
    }
    
    public void setSpecies(String species) {
        this.species = species;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public String getSoilType() {
        return soilType;
    }
    
    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }
}