// src/main/java/com/plantsim/controller/PlantController.java
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

import com.plantsim.model.EnvironmentalConditions;
import com.plantsim.model.Plant;
import com.plantsim.model.SimulationResult;
import com.plantsim.model.User;
import com.plantsim.service.PlantService;
import com.plantsim.service.SimulationService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/plants")
@CrossOrigin(origins = "http://localhost:4200")
public class PlantController {

    @Autowired
    private PlantService plantService;
    
    @Autowired
    private SimulationService simulationService;
    
    /**
     * Obtiene todas las plantas del usuario actual
     */
    @GetMapping
    public ResponseEntity<List<Plant>> getAllPlants(HttpServletRequest request) {
        User currentUser = (User) request.getAttribute("currentUser");
        List<Plant> plants = plantService.findAllByUser(currentUser);
        return new ResponseEntity<>(plants, HttpStatus.OK);
    }
    
    /**
     * Obtiene una planta por ID (verificando que pertenezca al usuario)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable("id") long id, HttpServletRequest request) {
        User currentUser = (User) request.getAttribute("currentUser");
        Plant plant = plantService.findByIdAndUser(id, currentUser);
        
        if (plant == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(plant, HttpStatus.OK);
    }
    
    /**
     * Crea una nueva planta asociada al usuario actual
     */
    @PostMapping
    public ResponseEntity<Plant> createPlant(@RequestBody Plant plant, HttpServletRequest request) {
        User currentUser = (User) request.getAttribute("currentUser");
        Plant newPlant = plantService.save(plant, currentUser);
        return new ResponseEntity<>(newPlant, HttpStatus.CREATED);
    }
    
    /**
     * Actualiza una planta existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<Plant> updatePlant(
            @PathVariable("id") long id, 
            @RequestBody Plant plant,
            HttpServletRequest request) {
        
        User currentUser = (User) request.getAttribute("currentUser");
        Plant updatedPlant = plantService.update(id, plant, currentUser);
        
        if (updatedPlant == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(updatedPlant, HttpStatus.OK);
    }
    
    /**
     * Elimina una planta
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePlant(@PathVariable("id") long id, HttpServletRequest request) {
        User currentUser = (User) request.getAttribute("currentUser");
        boolean deleted = plantService.deleteByIdAndUser(id, currentUser);
        
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    /**
     * Simula el crecimiento de una planta
     */
    @PostMapping("/{id}/simulate")
    public ResponseEntity<SimulationResult> simulateGrowth(
            @PathVariable("id") long id,
            @RequestBody EnvironmentalConditions conditions,
            HttpServletRequest request) {
        
        User currentUser = (User) request.getAttribute("currentUser");
        Plant plant = plantService.findByIdAndUser(id, currentUser);
        
        if (plant == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        SimulationResult result = simulationService.simulateGrowth(plant, conditions);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}