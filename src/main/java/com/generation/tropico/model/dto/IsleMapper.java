package com.generation.tropico.model.dto;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.generation.tropico.model.entities.Isle;

@Service
public class IsleMapper {

    @Autowired
    private BuildingMapper buildingMapper;

    /*Versione ottimizzata*/
    public IsleDTO toDTO(Isle isle) {
        IsleDTO dto = new IsleDTO();
        dto.setId(isle.getId());
        dto.setName(isle.getName());

        // Converte la lista di Building in BuildingDTO
        List<BuildingDTO> buildingsDTO = isle.getBuildings().stream()
            .map(buildingMapper::toDTO)
            .collect(Collectors.toList());

        dto.setBuildings(buildingsDTO);
        return dto;
    }
    
    /*Altra versione
     * public IsleDTO toDTO(Isle isle)
	{
		IsleDTO res = new IsleDTO();
		res.setId(isle.getId());
		res.setName(isle.getName());
		for(Building b:isle.getBuildings())
			res.getBuildings().add(buildingMapper.toDTO(b));
			*/
    
    

    // Converte da DTO a Entity
    public Isle fromDTO(IsleDTO dto) {
        Isle isle = new Isle();
        isle.setId(dto.getId());
        isle.setName(dto.getName());

        // Non gestiamo l'associazione di Building direttamente qui, sarà gestita separatamente
        return isle;
    }
}
