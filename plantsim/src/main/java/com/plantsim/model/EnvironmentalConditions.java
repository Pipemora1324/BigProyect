// src/main/java/com/plantsim/model/EnvironmentalConditions.java
package com.plantsim.model;

public class EnvironmentalConditions {
    private Double temperature;
    private Double humidity;
    private String soilType;
    private Integer lightHours;
    
    // Constructor vacío
    public EnvironmentalConditions() {
    }
    
    // Constructor con parámetros
    public EnvironmentalConditions(Double temperature, Double humidity, String soilType, Integer lightHours) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.soilType = soilType;
        this.lightHours = lightHours;
    }
    
    // Getters y setters
    public Double getTemperature() {
        return temperature;
    }
    
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
    
    public Double getHumidity() {
        return humidity;
    }
    
    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
    
    public String getSoilType() {
        return soilType;
    }
    
    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }
    
    public Integer getLightHours() {
        return lightHours;
    }
    
    public void setLightHours(Integer lightHours) {
        this.lightHours = lightHours;
    }
}