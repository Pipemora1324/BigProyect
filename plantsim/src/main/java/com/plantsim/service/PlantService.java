package com.plantsim.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.plantsim.model.Plant;

import jakarta.annotation.PostConstruct;

@Service
public class PlantService {
    
    // Simulamos una base de datos con una lista
    private List<Plant> plantas = new ArrayList<>();
    private Long idCounter = 0L;
    
    // Inicializar con datos de ejemplo
    @PostConstruct
    public void init() {
        // Añadir algunas plantas de ejemplo
        save(new Plant(null, "Cactus", "Cactaceae", 1, "arenoso"));
        save(new Plant(null, "Rosa", "Rosa spp.", 2, "franco"));
        save(new Plant(null, "Orquídea", "Orchidaceae", 3, "especial"));
        save(new Plant(null, "Aloe Vera", "Aloe barbadensis", 2, "arenoso"));
    }
    
    // Método para obtener todas las plantas
    public List<Plant> findAll() {
        return plantas;
    }
    
    // Método para encontrar una planta por ID
    public Plant findById(Long id) {
        Optional<Plant> planta = plantas.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        return planta.orElse(null);
    }
    
    // Método para guardar una nueva planta
    public Plant save(Plant plant) {
        if (plant.getId() == null) {
            plant.setId(++idCounter);
            plantas.add(plant);
        } else {
            // Si ya existe, actualizar
            plantas = plantas.stream()
                    .filter(p -> !p.getId().equals(plant.getId()))
                    .collect(Collectors.toList());
            plantas.add(plant);
        }
        return plant;
    }
    
    // Método para eliminar una planta
    public void deleteById(Long id) {
        plantas = plantas.stream()
                .filter(p -> !p.getId().equals(id))
                .collect(Collectors.toList());
    }
}