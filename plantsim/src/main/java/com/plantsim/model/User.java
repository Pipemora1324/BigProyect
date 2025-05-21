// src/main/java/com/plantsim/model/User.java
package com.plantsim.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    private String name;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Plant> plants = new ArrayList<>();
    
    // Constructor vacío
    public User() {
    }
    
    // Constructor con parámetros
    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
    
    // Getters y setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Plant> getPlants() {
        return plants;
    }
    
    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }
    
    // Métodos para gestionar la relación bidireccional
    public void addPlant(Plant plant) {
        plants.add(plant);
        plant.setUser(this);
    }
    
    public void removePlant(Plant plant) {
        plants.remove(plant);
        plant.setUser(null);
    }
}