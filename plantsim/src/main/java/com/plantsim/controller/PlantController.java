package com.plantsim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plantsim.model.CondicionesAmbientales;
import com.plantsim.model.Plant;
import com.plantsim.model.ResultadoSimulacion;
import com.plantsim.service.PlantService;
import com.plantsim.service.SimulacionService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200") // Para permitir conexiones desde Angular
public class PlantController {

    @Autowired
    private PlantService plantService;
    
    @Autowired
    private SimulacionService simulacionService;

    // Obtener todas las plantas
    @GetMapping("/plantas")
    public ResponseEntity<List<Plant>> getAllPlantas() {
        List<Plant> plantas = plantService.findAll();
        return new ResponseEntity<>(plantas, HttpStatus.OK);
    }
    
    // Obtener una planta por ID
    @GetMapping("/plantas/{id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable("id") long id) {
        Plant planta = plantService.findById(id);
        
        if (planta == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(planta, HttpStatus.OK);
    }
    
    // Crear una nueva planta
    @PostMapping("/plantas")
    public ResponseEntity<Plant> createPlant(@RequestBody Plant planta) {
        Plant nuevaPlanta = plantService.save(planta);
        return new ResponseEntity<>(nuevaPlanta, HttpStatus.CREATED);
    }
    
    // Actualizar una planta
    @PutMapping("/plantas/{id}")
    public ResponseEntity<Plant> updatePlant(@PathVariable("id") long id, @RequestBody Plant planta) {
        Plant plantaExistente = plantService.findById(id);
        
        if (plantaExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        planta.setId(id);
        plantService.save(planta);
        
        return new ResponseEntity<>(planta, HttpStatus.OK);
    }
    
    // Eliminar una planta
    @DeleteMapping("/plantas/{id}")
    public ResponseEntity<HttpStatus> deletePlant(@PathVariable("id") long id) {
        Plant plantaExistente = plantService.findById(id);
        
        if (plantaExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        plantService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    // Simular crecimiento
    @PostMapping("/plantas/{id}/simulate")
    public ResponseEntity<ResultadoSimulacion> simularCrecimiento(
            @PathVariable("id") long id,
            @RequestBody CondicionesAmbientales condiciones) {
        
        Plant planta = plantService.findById(id);
        
        if (planta == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        ResultadoSimulacion resultado = simulacionService.simularCrecimiento(planta, condiciones);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}