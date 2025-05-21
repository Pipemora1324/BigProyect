package com.plantsim.service;

import org.springframework.stereotype.Service;

import com.plantsim.model.CondicionesAmbientales;

@Service
public class RecomendacionService {

    public String generarRecomendacion(CondicionesAmbientales condiciones) {
        StringBuilder recomendacion = new StringBuilder();
        
        // Reglas basadas en temperatura
        if (condiciones.getTemperatura() < 10.0) {
            recomendacion.append("Evita el frío extremo, protege con invernadero. ");
        } else if (condiciones.getTemperatura() > 30.0) {
            recomendacion.append("La temperatura es demasiado alta, busca proporcionar sombra. ");
        }
        
        // Reglas basadas en humedad
        if (condiciones.getHumedad() < 30.0) {
            recomendacion.append("Riega más frecuentemente. ");
        } else if (condiciones.getHumedad() > 80.0) {
            recomendacion.append("Reduce el riego, hay riesgo de pudrición. ");
        }
        
        // Reglas basadas en tipo de suelo
        if ("arcilloso".equalsIgnoreCase(condiciones.getTipoSuelo())) {
            recomendacion.append("Evita excesos de agua, mejora el drenaje. ");
        } else if ("arenoso".equalsIgnoreCase(condiciones.getTipoSuelo())) {
            recomendacion.append("Requiere riegos más frecuentes pero en menor cantidad. ");
        }
        
        // Reglas basadas en luz
        if (condiciones.getHorasLuz() < 6) {
            recomendacion.append("Insuficiente luz, considera ubicarla en un lugar más luminoso. ");
        } else if (condiciones.getHorasLuz() > 14) {
            recomendacion.append("Exceso de luz, podría requerir algo de sombra. ");
        }
        
        return recomendacion.toString().trim();
    }
    
    public String evaluarEstadoSalud(CondicionesAmbientales condiciones) {
        // Contadores para evaluar condiciones
        int condicionesOptimas = 0;
        int condicionesRegulares = 0;
        int condicionesPesimas = 0;
        
        // Evaluar temperatura
        if (condiciones.getTemperatura() >= 15 && condiciones.getTemperatura() <= 25) {
            condicionesOptimas++;
        } else if (condiciones.getTemperatura() < 5 || condiciones.getTemperatura() > 35) {
            condicionesPesimas++;
        } else {
            condicionesRegulares++;
        }
        
        // Evaluar humedad
        if (condiciones.getHumedad() >= 40 && condiciones.getHumedad() <= 70) {
            condicionesOptimas++;
        } else if (condiciones.getHumedad() < 20 || condiciones.getHumedad() > 90) {
            condicionesPesimas++;
        } else {
            condicionesRegulares++;
        }
        
        // Evaluar luz
        if (condiciones.getHorasLuz() >= 8 && condiciones.getHorasLuz() <= 12) {
            condicionesOptimas++;
        } else if (condiciones.getHorasLuz() < 4 || condiciones.getHorasLuz() > 16) {
            condicionesPesimas++;
        } else {
            condicionesRegulares++;
        }
        
        // Determinar estado general
        if (condicionesOptimas >= 2 && condicionesPesimas == 0) {
            return "Excelente";
        } else if (condicionesOptimas >= 1 && condicionesPesimas <= 1) {
            return "Bueno";
        } else if (condicionesRegulares >= 2) {
            return "Regular";
        } else {
            return "Malo";
        }
    }
}