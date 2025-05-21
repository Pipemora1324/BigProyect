package com.plantsim.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.plantsim.model.Plant;

@Service
public class PlantService {
    
    // Simulamos una base de datos con una lista
    private List<Plant> plants = new ArrayList<>();
    private Long idCounter = 0L;
    
    // Constructor que añade algunas plantas de ejemplo
    public PlantService() {
        // Añadir algunas plantas de ejemplo
        save(new Plant(null, "Cactus", "Cactaceae", 1, "arenoso"));
        save(new Plant(null, "Rosa", "Rosa spp.", 2, "franco"));
        save(new Plant(null, "Orquídea", "Orchidaceae", 3, "especial"));
    }
    
    // Método para obtener todas las plantas
    public List<Plant> findAll() {
        return plants;
    }
    
    // Método para encontrar una planta por ID
    public Plant findById(Long id) {
        return plants.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    // Método para guardar una nueva planta
    public Plant save(Plant plant) {
        if (plant.getId() == null) {
            plant.setId(++idCounter);
            plants.add(plant);
        } else {
            // Si ya existe, actualizar
            plants = plants.stream()
                    .filter(p -> !p.getId().equals(plant.getId()))
                    .collect(Collectors.toList());
            plants.add(plant);
        }
        return plant;
    }
    
    // Método para eliminar una planta
    public void deleteById(Long id) {
        plants = plants.stream()
                .filter(p -> !p.getId().equals(id))
                .collect(Collectors.toList());
    }
}