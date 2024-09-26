package com.generation.tropico.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.tropico.model.entities.Building;

public interface BuildingRepository extends JpaRepository<Building, Integer> {

}
