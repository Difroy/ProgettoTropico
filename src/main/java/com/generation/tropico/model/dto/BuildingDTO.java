package com.generation.tropico.model.dto;

import java.util.List;



public class BuildingDTO {
  
	  private int id;
	    private String name;
	    private String address;
	    private String buildingType; // Discriminatory column

	    // Residential fields
	    private Integer rent;
	    private Integer capacity;
	    private List<TropicanoDTO> residents;  // List of TropicanoDTO for residents

	    // Industry fields
	    private Integer production;
	    private Integer jobs;
	    private String product;
	    private List<TropicanoDTO> workers;  // List of TropicanoDTO for workers

	    // Getters and Setters
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

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public String getBuildingType() {
	        return buildingType;
	    }

	    public void setBuildingType(String buildingType) {
	        this.buildingType = buildingType;
	    }

	    public Integer getRent() {
	        return rent;
	    }

	    public void setRent(Integer rent) {
	        this.rent = rent;
	    }

	    public Integer getCapacity() {
	        return capacity;
	    }

	    public void setCapacity(Integer capacity) {
	        this.capacity = capacity;
	    }

	    public List<TropicanoDTO> getResidents() {
	        return residents;
	    }

	    public void setResidents(List<TropicanoDTO> residents) {
	        this.residents = residents;
	    }

	    public Integer getProduction() {
	        return production;
	    }

	    public void setProduction(Integer production) {
	        this.production = production;
	    }

	    public Integer getJobs() {
	        return jobs;
	    }

	    public void setJobs(Integer jobs) {
	        this.jobs = jobs;
	    }

	    public String getProduct() {
	        return product;
	    }

	    public void setProduct(String product) {
	        this.product = product;
	    }

	    public List<TropicanoDTO> getWorkers() {
	        return workers;
	    }

	    public void setWorkers(List<TropicanoDTO> workers) {
	        this.workers = workers;
	    }
    
}
