// src/main/java/com/plantsim/repository/PlantRepository.java
package com.plantsim.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plantsim.model.Plant;
import com.plantsim.model.User;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {
    List<Plant> findByUser(User user);
    Optional<Plant> findByIdAndUser(Long id, User user);
}