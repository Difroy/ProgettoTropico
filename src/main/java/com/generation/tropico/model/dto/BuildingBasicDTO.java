package com.generation.tropico.model.dto;

public class BuildingBasicDTO {

	int id;
	String name, buildingType;
	
	public BuildingBasicDTO() {}
	
	public BuildingBasicDTO(int id, String name, String buildingType)
	{
		this.id = id;
		this.name = name;
		this.buildingType = buildingType;
	}
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

	public String getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}


	
	
	
}
