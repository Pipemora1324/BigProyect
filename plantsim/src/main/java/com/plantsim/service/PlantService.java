// src/main/java/com/plantsim/service/PlantService.java
package com.plantsim.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plantsim.model.Plant;
import com.plantsim.model.User;
import com.plantsim.repository.PlantRepository;

@Service
public class PlantService {
    
    @Autowired
    private PlantRepository plantRepository;
    
    /**
     * Obtiene todas las plantas de un usuario
     */
    public List<Plant> findAllByUser(User user) {
        return plantRepository.findByUser(user);
    }
    
    /**
     * Busca una planta por ID y usuario
     */
    public Plant findByIdAndUser(Long id, User user) {
        Optional<Plant> plant = plantRepository.findByIdAndUser(id, user);
        return plant.orElse(null);
    }
    
    /**
     * Guarda una planta asociada a un usuario
     */
    public Plant save(Plant plant, User user) {
        plant.setUser(user);
        return plantRepository.save(plant);
    }
    
    /**
     * Actualiza una planta existente
     */
    public Plant update(Long id, Plant plantData, User user) {
        Optional<Plant> existingPlantOpt = plantRepository.findByIdAndUser(id, user);
        
        if (existingPlantOpt.isPresent()) {
            Plant existingPlant = existingPlantOpt.get();
            existingPlant.setName(plantData.getName());
            existingPlant.setSpecies(plantData.getSpecies());
            existingPlant.setAge(plantData.getAge());
            existingPlant.setSoilType(plantData.getSoilType());
            
            return plantRepository.save(existingPlant);
        } else {
            return null;
        }
    }
    
    /**
     * Elimina una planta por ID y usuario
     */
    public boolean deleteByIdAndUser(Long id, User user) {
        Optional<Plant> plantOpt = plantRepository.findByIdAndUser(id, user);
        
        if (plantOpt.isPresent()) {
            plantRepository.delete(plantOpt.get());
            return true;
        } else {
            return false;
        }
    }
}