package com.plantsim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plantsim.model.CondicionesAmbientales;
import com.plantsim.model.Plant;
import com.plantsim.model.ResultadoSimulacion;

@Service
public class SimulacionService {

    @Autowired
    private RecomendacionService recomendacionService;
    
    public ResultadoSimulacion simularCrecimiento(Plant planta, CondicionesAmbientales condiciones) {
        // Calcular crecimiento basado en condiciones ambientales
        double factorTemperatura = calcularFactorTemperatura(condiciones.getTemperatura());
        double factorHumedad = calcularFactorHumedad(condiciones.getHumedad());
        double factorLuz = calcularFactorLuz(condiciones.getHorasLuz());
        double factorSuelo = calcularFactorSuelo(condiciones.getTipoSuelo());
        
        // Promedio ponderado de factores
        double crecimiento = (factorTemperatura * 0.3) + (factorHumedad * 0.3) + 
                             (factorLuz * 0.25) + (factorSuelo * 0.15);
        
        // Redondear a 2 decimales
        crecimiento = Math.round(crecimiento * 100.0) / 100.0;
        
        // Generar recomendación basada en condiciones
        String recomendacion = recomendacionService.generarRecomendacion(condiciones);
        
        // Evaluar estado de salud
        String estadoSalud = recomendacionService.evaluarEstadoSalud(condiciones);
        
        return new ResultadoSimulacion(crecimiento, recomendacion, estadoSalud);
    }
    
    private double calcularFactorTemperatura(double temperatura) {
        if (temperatura >= 15 && temperatura <= 25) {
            return 1.0; // Temperatura óptima
        } else if (temperatura < 5 || temperatura > 35) {
            return 0.3; // Temperatura extrema
        } else {
            return 0.7; // Temperatura moderada
        }
    }
    
    private double calcularFactorHumedad(double humedad) {
        if (humedad >= 40 && humedad <= 70) {
            return 1.0; // Humedad óptima
        } else if (humedad < 20 || humedad > 90) {
            return 0.3; // Humedad extrema
        } else {
            return 0.7; // Humedad moderada
        }
    }
    
    private double calcularFactorLuz(int horasLuz) {
        if (horasLuz >= 8 && horasLuz <= 12) {
            return 1.0; // Luz óptima
        } else if (horasLuz < 4 || horasLuz > 16) {
            return 0.4; // Luz extrema
        } else {
            return 0.8; // Luz moderada
        }
    }
    
    private double calcularFactorSuelo(String tipoSuelo) {
        switch (tipoSuelo.toLowerCase()) {
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