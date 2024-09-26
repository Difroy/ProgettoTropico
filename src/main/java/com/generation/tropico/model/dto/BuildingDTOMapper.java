package com.generation.tropico.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.generation.tropico.model.entities.Building;
import com.generation.tropico.model.entities.Industry;
import com.generation.tropico.model.entities.Product;
import
com.generation.tropico.model.entities.Residential;

@Component
public class BuildingDTOMapper {

	
	private final TropicanoDTOMapper tropicanoDTOMapper;
	 
	 
	 public BuildingDTOMapper(TropicanoDTOMapper tropicanoDTOMapper) {
	        this.tropicanoDTOMapper = tropicanoDTOMapper;
	    }
	    
	    
	    public Building toEntity(BuildingDTO dto) {
	        if (dto.getBuildingType().equals("Residential")) {
	            Residential residential = new Residential();
	            residential.setId(dto.getId());
	            residential.setName(dto.getName());
	            residential.setAddress(dto.getAddress());
	            residential.setRent(dto.getRent());
	            residential.setCapacity(dto.getCapacity());
	            // You can set other properties if needed
	            return residential;
	        } else if (dto.getBuildingType().equals("Industry")) {
	            Industry industry = new Industry();
	            industry.setId(dto.getId());
	            industry.setName(dto.getName());
	            industry.setAddress(dto.getAddress());
	            industry.setProduction(dto.getProduction());
	            industry.setJobs(dto.getJobs());
	            industry.setProduct(Product.valueOf(dto.getProduct()));
	            // You can set other properties if needed
	            return industry;
	        } else {
	            throw new IllegalArgumentException("Invalid building type: " + dto.getBuildingType());
	        }
	    }
	    
	    public BuildingDTO toDTO(Building building) 
	    {
	        BuildingDTO dto = new BuildingDTO();
	        dto.setId(building.getId());
	        dto.setName(building.getName());
	        dto.setAddress(building.getAddress());

	        if (building instanceof Residential) 
	        {
	            dto.setBuildingType("Residential");
	            Residential residential = (Residential) building;
	            dto.setRent(residential.getRent());
	            dto.setCapacity(residential.getCapacity());
	            dto.setResidents(residential.getResidents().stream()
	                    .map(tropicanoDTOMapper::toDTO)
	                    .collect(Collectors.toList()));  // Map residents to TropicanoDTO
	        } 
	        else if (building instanceof Industry) 
	        {
	            dto.setBuildingType("Industry");
	            Industry industry = (Industry) building;
	            dto.setProduction(industry.getProduction());
	            dto.setJobs(industry.getJobs());
	            dto.setProduct(industry.getProduct().toString());
	            dto.setWorkers(industry.getWorkers().stream()
	                    .map(tropicanoDTOMapper::toDTO)
	                    .collect(Collectors.toList()));  // Map workers to TropicanoDTO
	        }

	        return dto;
	    }
	    
	    public List<BuildingBasicDTO> toBasicDTO(List<Building> buildings)
	    {
	    	List<BuildingBasicDTO> res = new ArrayList<BuildingBasicDTO>();
	    	for(Building b:buildings)
	    		res.add(new BuildingBasicDTO(b.getId(), b.getName() , b.getClass().getSimpleName()));
	    	
	    	return res;
	    }
	
	
	
}
