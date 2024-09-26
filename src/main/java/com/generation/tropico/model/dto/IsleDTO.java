package com.generation.tropico.model.dto;
import java.util.List;

public class IsleDTO {
    private int id;
    private String name;
    private List<BuildingDTO> buildings; // Una lista di BuildingDTO per rappresentare gli edifici sull'isola

    // Costruttore senza argomenti
    public IsleDTO() {}

    // Costruttore con argomenti
    public IsleDTO(int id, String name, List<BuildingDTO> buildings) {
        this.id = id;
        this.name = name;
        this.buildings = buildings;
    }

    // Getter e Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BuildingDTO> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<BuildingDTO> buildings) {
        this.buildings = buildings;
    }
}
