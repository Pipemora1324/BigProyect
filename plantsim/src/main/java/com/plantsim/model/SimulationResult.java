// src/main/java/com/plantsim/model/SimulationResult.java
package com.plantsim.model;

public class SimulationResult {
    private Double growth;
    private String recommendation;
    private String healthStatus;
    
    // Constructor vacío
    public SimulationResult() {
    }
    
    // Constructor con parámetros
    public SimulationResult(Double growth, String recommendation, String healthStatus) {
        this.growth = growth;
        this.recommendation = recommendation;
        this.healthStatus = healthStatus;
    }
    
    // Getters y setters
    public Double getGrowth() {
        return growth;
    }
    
    public void setGrowth(Double growth) {
        this.growth = growth;
    }
    
    public String getRecommendation() {
        return recommendation;
    }
    
    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
    
    public String getHealthStatus() {
        return healthStatus;
    }
    
    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }
}