package com.plantsim.service;

import org.springframework.stereotype.Service;

import com.plantsim.model.EnvironmentalConditions;

@Service
public class RecommendationService {

    public String generarRecomendacion(EnvironmentalConditions conditions) {
        StringBuilder recommendation = new StringBuilder();
        
        // Reglas basadas en temperatura
        if (conditions.getTemperature() < 10.0) {
            recommendation.append("Evita el frío extremo, protege con invernadero. ");
        } else if (conditions.getTemperature() > 30.0) {
            recommendation.append("La temperatura es demasiado alta, busca proporcionar sombra. ");
        }
        
        // Reglas basadas en humedad
        if (conditions.getHumidity() < 30.0) {
            recommendation.append("Riega más frecuentemente. ");
        } else if (conditions.getHumidity() > 80.0) {
            recommendation.append("Reduce el riego, hay riesgo de pudrición. ");
        }
        
        // Reglas basadas en tipo de suelo
        if ("arcilloso".equalsIgnoreCase(conditions.getSoilType())) {
            recommendation.append("Evita excesos de agua, mejora el drenaje. ");
        } else if ("arenoso".equalsIgnoreCase(conditions.getSoilType())) {
            recommendation.append("Requiere riegos más frecuentes pero en menor cantidad. ");
        }
        
        // Reglas basadas en luz
        if (conditions.getDaylightHours() < 6) {
            recommendation.append("Insuficiente luz, considera ubicarla en un lugar más luminoso. ");
        } else if (conditions.getDaylightHours() > 14) {
            recommendation.append("Exceso de luz, podría requerir algo de sombra. ");
        }
        
        return recommendation.toString().trim();
    }
    
    public String evaluateHealthStatus(EnvironmentalConditions conditions) {
        // Contadores para evaluar condiciones
        int optimalConditions = 0;
        int regularConditions = 0;
        int terribleConditions = 0;
        
        // Evaluar temperatura
        if (conditions.getTemperature() >= 15 && conditions.getTemperature() <= 25) {
            optimalConditions++;
        } else if (conditions.getTemperature() < 5 || conditions.getTemperature() > 35) {
            terribleConditions++;
        } else {
            regularConditions++;
        }
        
        // Evaluar humedad
        if (conditions.getHumidity() >= 40 && conditions.getHumidity() <= 70) {
            optimalConditions++;
        } else if (conditions.getHumidity() < 20 || conditions.getHumidity() > 90) {
            terribleConditions++;
        } else {
            regularConditions++;
        }
        
        // Evaluar luz
        if (conditions.getDaylightHours() >= 8 && conditions.getDaylightHours() <= 12) {
            optimalConditions++;
        } else if (conditions.getDaylightHours() < 4 || conditions.getDaylightHours() > 16) {
            terribleConditions++;
        } else {
            regularConditions++;
        }
        
        // Determinar estado general
        if (optimalConditions >= 2 && terribleConditions == 0) {
            return "Excelente";
        } else if (optimalConditions >= 1 && terribleConditions <= 1) {
            return "Bueno";
        } else if (regularConditions >= 2) {
            return "Regular";
        } else {
            return "Malo";
        }
    }
}