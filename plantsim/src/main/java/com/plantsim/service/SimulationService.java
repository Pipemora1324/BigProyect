// src/main/java/com/plantsim/service/SimulationService.java
package com.plantsim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plantsim.model.EnvironmentalConditions;
import com.plantsim.model.Plant;
import com.plantsim.model.SimulationResult;

@Service
public class SimulationService {

    @Autowired
    private RecommendationService recommendationService;
    
    /**
     * Simula el crecimiento de una planta basado en condiciones ambientales
     */
    public SimulationResult simulateGrowth(Plant plant, EnvironmentalConditions conditions) {
        // Calcular crecimiento basado en condiciones ambientales
        double temperatureFactor = calculateTemperatureFactor(conditions.getTemperature());
        double humidityFactor = calculateHumidityFactor(conditions.getHumidity());
        double lightFactor = calculateLightFactor(conditions.getLightHours());
        double soilFactor = calculateSoilFactor(conditions.getSoilType());
        
        // Promedio ponderado de factores
        double growth = (temperatureFactor * 0.3) + (humidityFactor * 0.3) + 
                         (lightFactor * 0.25) + (soilFactor * 0.15);
        
        // Redondear a 2 decimales
        growth = Math.round(growth * 100.0) / 100.0;
        
        // Generar recomendación basada en condiciones
        String recommendation = recommendationService.generateRecommendation(conditions);
        
        // Evaluar estado de salud
        String healthStatus = recommendationService.evaluateHealthStatus(conditions);
        
        return new SimulationResult(growth, recommendation, healthStatus);
    }
    
    private double calculateTemperatureFactor(double temperature) {
        if (temperature >= 15 && temperature <= 25) {
            return 1.0; // Temperatura óptima
        } else if (temperature < 5 || temperature > 35) {
            return 0.3; // Temperatura extrema
        } else {
            return 0.7; // Temperatura moderada
        }
    }
    
    private double calculateHumidityFactor(double humidity) {
        if (humidity >= 40 && humidity <= 70) {
            return 1.0; // Humedad óptima
        } else if (humidity < 20 || humidity > 90) {
            return 0.3; // Humedad extrema
        } else {
            return 0.7; // Humedad moderada
        }
    }
    
    private double calculateLightFactor(int lightHours) {
        if (lightHours >= 8 && lightHours <= 12) {
            return 1.0; // Luz óptima
        } else if (lightHours < 4 || lightHours > 16) {
            return 0.4; // Luz extrema
        } else {
            return 0.8; // Luz moderada
        }
    }
    
    private double calculateSoilFactor(String soilType) {
        switch (soilType.toLowerCase()) {
            case "franco":
                return 1.0; // Suelo óptimo
            case "arcilloso":
            case "arenoso":
                return 0.7; // Suelos con limitaciones
            default:
                return 0.5;
        }
    }
}