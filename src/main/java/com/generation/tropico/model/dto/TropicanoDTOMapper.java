package com.generation.tropico.model.dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.tropico.model.entities.Education;
import com.generation.tropico.model.entities.Gender;
import com.generation.tropico.model.entities.Industry;
import com.generation.tropico.model.entities.Party;
import com.generation.tropico.model.entities.Religion;
import com.generation.tropico.model.entities.Residential;
import com.generation.tropico.model.entities.Tropicano;
import com.generation.tropico.model.repository.BuildingRepository;


@Service
public class TropicanoDTOMapper {

	
	@Autowired
	BuildingRepository buildingRepo;
	
    public TropicanoDTO toDTO(Tropicano tropicano) 
    {
        TropicanoDTO dto = new TropicanoDTO();

        dto.setId(tropicano.getId());
        dto.setName(tropicano.getName());
        dto.setSurname(tropicano.getSurname());
        dto.setBirthyear(tropicano.getBirthyear());
        dto.setGender(tropicano.getGender().name());
        dto.setEducation(tropicano.getEducation().name());
        dto.setParty(tropicano.getParty().name());
        dto.setSatisfaction(tropicano.getSatisfaction());
        dto.setImage(tropicano.getImage());
        dto.setReligion(tropicano.getReligion().name());

        // Map Residential
        if (tropicano.getResidential() != null) {
            dto.setResidentialId(tropicano.getResidential().getId());
            dto.setResidentialName(tropicano.getResidential().getName());
        } else {
            dto.setResidentialId(null);
            dto.setResidentialName(null);
        }

        // Map Industry
        if (tropicano.getIndustry() != null) {
            dto.setIndustryId(tropicano.getIndustry().getId());
            dto.setIndustryName(tropicano.getIndustry().getName());
        } else {
            dto.setIndustryId(null);
            dto.setIndustryName(null);
        }

        return dto;
    }

    // equivalente di toDTO
    public Tropicano toEntity(TropicanoDTO dto) 
    {
        Tropicano tropicano = new Tropicano();

        tropicano.setId(dto.getId());
        tropicano.setName(dto.getName());
        tropicano.setSurname(dto.getSurname());
        tropicano.setBirthyear(dto.getBirthyear());
        tropicano.setGender(Gender.valueOf(dto.getGender()));
        tropicano.setEducation(Education.valueOf(dto.getEducation()));
        tropicano.setParty(Party.valueOf(dto.getParty()));
        tropicano.setSatisfaction(dto.getSatisfaction());
        tropicano.setImage(dto.getImage());
        tropicano.setReligion(Religion.valueOf(dto.getReligion()));
        
        // recupero eventuali padri...
        if(dto.getResidentialId()>0)
        	tropicano.setResidential((Residential)(buildingRepo.findById(dto.getResidentialId()).get()));

        if(dto.getIndustryId()>0)
        	tropicano.setIndustry((Industry)(buildingRepo.findById(dto.getIndustryId()).get()));
        
        return tropicano;
    }
	
	
	
	
}
