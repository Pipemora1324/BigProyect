package com.plantsim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plantsim.model.EnvironmentalConditions;
import com.plantsim.model.Plant;
import com.plantsim.model.SimulationResult;

@Service
public class SimulationService {

    @Autowired
    private RecommendationService RecommendationService;
    
    public SimulationResult simulateGrowth(Plant plant, EnvironmentalConditions conditions) {
        // Calcular crecimiento basado en condiciones ambientales
        double factorTemperature = calculateTemperatureFactor(conditions.getTemperature());
        double factorHumidity = calculateHumidityFactor(conditions.getHumidity());
        double lightFactor = calculateLightFactor(conditions.getDaylightHours());
        double floorFactor = calculateFloorFactor(conditions.getSoilType());
        
        // Promedio ponderado de factores
        double growth = (factorTemperature * 0.3) + (factorHumidity * 0.3) + 
                             (lightFactor * 0.25) + (floorFactor * 0.15);
        
        // Redondear a 2 decimales
        growth = Math.round(growth * 100.0) / 100.0;
        
        // Generar recomendación basada en condiciones
        String recommendation = RecommendationService.generarRecomendacion(conditions);
        
        // Evaluar estado de salud
        String healthStatus = RecommendationService.evaluateHealthStatus(conditions);
        
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
    
    private double calculateLightFactor(int daylightHours) {
        if (daylightHours >= 8 && daylightHours <= 12) {
            return 1.0; // Luz óptima
        } else if (daylightHours < 4 || daylightHours > 16) {
            return 0.4; // Luz extrema
        } else {
            return 0.8; // Luz moderada
        }
    }
    
   private double calculateFloorFactor(String soilType) {
    return switch (soilType.toLowerCase()) {
        case "franco" -> 1.0;
        case "arcilloso", "arenoso" -> 0.7;
        default -> 0.5;
    };
}
}