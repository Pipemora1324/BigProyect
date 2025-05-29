package com.plantsim.model;

public class EnvironmentalConditions {
    private Double temperature;
    private Double humidity;
    private String soilType;
    private Integer daylightHours;
    
    // Constructor vacío
    public EnvironmentalConditions() {
    }
    
    // Constructor con parámetros
    public EnvironmentalConditions(Double temperature, Double humidity, String soilType, Integer daylightHours) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.soilType = soilType;
        this.daylightHours = daylightHours;
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
    
    public Integer getDaylightHours() {
        return daylightHours;
    }
    
    public void setDaylightHours(Integer daylightHours) {
        this.daylightHours = daylightHours;
    }
}