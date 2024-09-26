package com.generation.tropico.model.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("Industry")
public class Industry extends Building {

   
    private int production;
    private int jobs;
    private Product product;

    
    @OneToMany(mappedBy = "industry")
    private List<Tropicano> workers = new ArrayList<>();


	public int getProduction() {
		return production;
	}


	public void setProduction(int setterProduction) {
		this.production = setterProduction;
	}


	public int getJobs() {
		return jobs;
	}


	public void setJobs(int setterJobs) {
		this.jobs = setterJobs;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product setterProduct) {
		this.product = setterProduct;
	}


	public List<Tropicano> getWorkers() {
		return workers;
	}


	public void setWorkers(List<Tropicano> setterWorkers) {
		this.workers = setterWorkers;
	}
    
    
    
    
    
    
    
}

