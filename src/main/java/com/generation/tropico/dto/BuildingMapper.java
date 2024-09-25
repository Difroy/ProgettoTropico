package com.generation.tropico.dto;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.tropico.model.entities.Building;
import com.generation.tropico.model.entities.Industry;
import com.generation.tropico.model.entities.Isle;
import com.generation.tropico.model.entities.Residential;
import com.generation.tropico.model.repository.IsleRepository;

@Service
public class BuildingMapper {

    @Autowired
    IsleRepository isleRepo;

    // Converte da Entity a DTO
    public BuildingDTO toDTO(Building b) {
        BuildingDTO dto = new BuildingDTO();
        
        dto.setId(b.getId());
        dto.setName(b.getName());
        dto.setArea(b.getArea());
        dto.setX1(b.getX1());
        dto.setX2(b.getX2());
        dto.setY1(b.getY1());
        dto.setY2(b.getY2());
        dto.setIsleId(b.getIsle().getId());
        dto.setIsleName(b.getIsle().getName());

        if (b instanceof Residential) {
            Residential r = (Residential) b;
            dto.setRent(r.getRent());
            dto.setCapacity(r.getCapacity());
            dto.setBuildingType("Residential");
        }

        if (b instanceof Industry) {
            Industry i = (Industry) b;
            dto.setJobs(i.getJobs());
            dto.setProduct(i.getProduct());
            dto.setQuantity(i.getQuantity());
            dto.setBuildingType("Industry");
        }

        return dto;
    }

    // Converte da DTO a Entity
    public Building fromDTO(BuildingDTO dto) {
        if (dto.getIsleId() <= 0)
            throw new RuntimeException("Invalid island " + dto.getIsleId());

        Optional<Isle> isleOptional = isleRepo.findById(dto.getIsleId());

        if (isleOptional.isEmpty())
            throw new RuntimeException("Invalid island " + dto.getIsleId());

        Isle isle = isleOptional.get();

        switch (dto.getBuildingType()) {
            case "Residential":
                return dtoToResidential(dto, isle);
            case "Industry":
                return dtoToIndustry(dto, isle);
            default:
                throw new RuntimeException("Unsupported building type " + dto.getBuildingType());
        }
    }

    private Building dtoToIndustry(BuildingDTO dto, Isle isle) {
        Industry res = new Industry();
        res.setIsle(isle);
        res.setId(dto.getId());
        res.setName(dto.getName());
        res.setArea(dto.getArea());
        res.setProduct(dto.getProduct());
        res.setQuantity(dto.getQuantity());
        res.setJobs(dto.getJobs());
        res.setX1(dto.getX1());
        res.setX2(dto.getX2());
        res.setY1(dto.getY1());
        res.setY2(dto.getY2());
        return res;
    }

    private Building dtoToResidential(BuildingDTO dto, Isle isle) {
        Residential res = new Residential();
        res.setIsle(isle);
        res.setId(dto.getId());
        res.setName(dto.getName());
        res.setArea(dto.getArea());
        res.setCapacity(dto.getCapacity());
        res.setRent(dto.getRent());
        res.setX1(dto.getX1());
        res.setX2(dto.getX2());
        res.setY1(dto.getY1());
        res.setY2(dto.getY2());
        return res;
    }
}
