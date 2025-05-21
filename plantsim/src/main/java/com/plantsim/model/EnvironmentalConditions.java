package com.plantsim.model;

public class CondicionesAmbientales {
    private Double temperatura;
    private Double humedad;
    private String tipoSuelo;
    private Integer horasLuz;
    
    // Constructor vacío
    public CondicionesAmbientales() {
    }
    
    // Constructor con parámetros
    public CondicionesAmbientales(Double temperatura, Double humedad, String tipoSuelo, Integer horasLuz) {
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.tipoSuelo = tipoSuelo;
        this.horasLuz = horasLuz;
    }
    
    // Getters y setters
    public Double getTemperatura() {
        return temperatura;
    }
    
    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }
    
    public Double getHumedad() {
        return humedad;
    }
    
    public void setHumedad(Double humedad) {
        this.humedad = humedad;
    }
    
    public String getTipoSuelo() {
        return tipoSuelo;
    }
    
    public void setTipoSuelo(String tipoSuelo) {
        this.tipoSuelo = tipoSuelo;
    }
    
    public Integer getHorasLuz() {
        return horasLuz;
    }
    
    public void setHorasLuz(Integer horasLuz) {
        this.horasLuz = horasLuz;
    }
}