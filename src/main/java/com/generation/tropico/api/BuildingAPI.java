package com.generation.tropico.api;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.generation.tropico.model.dto.BuildingBasicDTO;
import com.generation.tropico.model.dto.BuildingDTO;
import com.generation.tropico.model.dto.BuildingDTOMapper;
import com.generation.tropico.model.entities.Building;
import com.generation.tropico.model.repository.BuildingRepository;

@RestController
@CrossOrigin
@RequestMapping("/tropico/api/buildings")
public class BuildingAPI {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingDTOMapper buildingDTOMapper;

    // GET all buildings
    @GetMapping
    public List<BuildingDTO> getAllBuildings() 
    {
        List<Building> buildings = buildingRepository.findAll();
      
        
        return 	buildings
        		.stream()
                .map(buildingDTOMapper::toDTO)
                .collect(Collectors.toList());
        
        //return 	buildings
        //		.stream()
        //        .map(building->buildingDTOMapper.toDTO(building))
        //        .collect(Collectors.toList());
        
        
        /*
         * List<Building> res = new ArrayList<Building>();
         * for(Building b:buildings)
         * 		res.add(buildingDTOMapper.toDTO(b));
         * 
         * 
         * 
         */  
    }
    // GET building by ID
    
    
    
    @GetMapping("/{id}")
    public ResponseEntity<BuildingDTO> getBuildingById(@PathVariable int id) {
    	Optional<Building> building = buildingRepository.findById(id);
        return building
        		.map(b -> ResponseEntity.ok(buildingDTOMapper.toDTO(b)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*
     * 
     *	Optional<Building> building = buildingRepository.findById(id);
     * 	if(building.isPresent())
     * 		return new ResponseEntity<BuildingDTO>(buildingDTOMapper.toDTO(building.get(), HttpStatus.OK);
     *  else
     * 		return new ResponseEntity<BuildingDTO>(HttpStatus.NOT_FOUND);
     * 
     */
      
    // POST a new building
    @PostMapping
    public ResponseEntity<BuildingDTO> createBuilding(@RequestBody BuildingDTO buildingDTO) {
        Building b = buildingDTOMapper.toEntity(buildingDTO);
        
      b = buildingRepository.save(b);
        return ResponseEntity.ok(buildingDTOMapper.toDTO(b));
    }
    
    // PUT (update) a building by ID
    @PutMapping("/{id}")
    public ResponseEntity<BuildingDTO> updateBuilding(@PathVariable int id, @RequestBody BuildingDTO buildingDTO) {
        Optional<Building> buildingOpt = buildingRepository.findById(id);
        if (buildingOpt.isPresent()) {
            Building buildingToUpdate = buildingDTOMapper.toEntity(buildingDTO);
            buildingToUpdate.setId(id); // Imposta l'ID del building esistente
            Building updatedBuilding = buildingRepository.save(buildingToUpdate);
            return ResponseEntity.ok(buildingDTOMapper.toDTO(updatedBuilding));
        }
        return ResponseEntity.notFound().build();
    }

    
    
   
    
    // DELETE a building by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable int id) 
    {
        Optional<Building> building = buildingRepository.findById(id);

        if (building.isPresent()) {
            buildingRepository.delete(building.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @GetMapping("/basicinfo")
    public List<BuildingBasicDTO> getBasicInfo()
    {
    	return buildingDTOMapper.toBasicDTO(buildingRepository.findAll());
    
    }
}
    

