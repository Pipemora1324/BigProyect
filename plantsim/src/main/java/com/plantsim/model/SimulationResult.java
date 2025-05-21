package com.plantsim.model;

public class ResultadoSimulacion {
    private Double crecimiento;
    private String recomendacion;
    private String estadoSalud;
    
    // Constructor vacío
    public ResultadoSimulacion() {
    }
    
    // Constructor con parámetros
    public ResultadoSimulacion(Double crecimiento, String recomendacion, String estadoSalud) {
        this.crecimiento = crecimiento;
        this.recomendacion = recomendacion;
        this.estadoSalud = estadoSalud;
    }
    
    // Getters y setters
    public Double getCrecimiento() {
        return crecimiento;
    }
    
    public void setCrecimiento(Double crecimiento) {
        this.crecimiento = crecimiento;
    }
    
    public String getRecomendacion() {
        return recomendacion;
    }
    
    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }
    
    public String getEstadoSalud() {
        return estadoSalud;
    }
    
    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }
}