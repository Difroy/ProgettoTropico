package com.generation.tropico.api;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.tropico.dto.BuildingDTO;
import com.generation.tropico.dto.BuildingMapper;
import com.generation.tropico.model.entities.Building;
import com.generation.tropico.model.repository.BuildingRepository;

@RestController
@RequestMapping("/api/buildings")
public class BuildingAPI {

    @Autowired
    private BuildingRepository buildingRepo;

    @Autowired
    private BuildingMapper buildingMapper;

    // GET all buildings
    @GetMapping
    public List<BuildingDTO> getAllBuildings() {
        List<Building> buildings = buildingRepo.findAll();
        return buildings.stream()
                .map(buildingMapper::toDTO)
                .toList();
        
        // return buildings
        //.stream()
        //.map(building -> buildingMapper.toDTO(building))
        //collect(Collectors.toList());
        
        /*
         * List<Building> res = new ArrayList<>();
         * for(Building b : buildings) 
         *     res.add(buildingMapper.toDTO(b));
         *Una landa: modo di scrivere. 
         * 
         * */

    }

    // GET building by ID
    @GetMapping("/{id}")
    public ResponseEntity<BuildingDTO> getBuildingById(@PathVariable Long id) {
        Optional<Building> buildingOpt = buildingRepo.findById(id);
        if (buildingOpt.isPresent()) {
            return ResponseEntity.ok(buildingMapper.toDTO(buildingOpt.get()));
        }
        return ResponseEntity.notFound().build();
    }

    // POST a new building
    @PostMapping
    public ResponseEntity<BuildingDTO> createBuilding(@RequestBody BuildingDTO buildingDTO) {
        Building building = buildingMapper.fromDTO(buildingDTO);
        Building savedBuilding = buildingRepo.save(building);
        return ResponseEntity.ok(buildingMapper.toDTO(savedBuilding));
    }

    // PUT (update) a building by ID
    @PutMapping("/{id}")
    public ResponseEntity<BuildingDTO> updateBuilding(@PathVariable Long id, @RequestBody BuildingDTO buildingDTO) {
        Optional<Building> buildingOpt = buildingRepo.findById(id);
        if (buildingOpt.isPresent()) {
            Building buildingToUpdate = buildingMapper.fromDTO(buildingDTO);
            buildingToUpdate.setId(id); // Imposta l'ID del building esistente
            Building updatedBuilding = buildingRepo.save(buildingToUpdate);
            return ResponseEntity.ok(buildingMapper.toDTO(updatedBuilding));
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE a building by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable Long id) {
        if (buildingRepo.existsById(id)) {
            buildingRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
