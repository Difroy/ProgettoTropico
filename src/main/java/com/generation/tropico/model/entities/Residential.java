package com.generation.tropico.model.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("Residential")
public class Residential extends Building {

    private int rent;
    private int capacity;
    
    @OneToMany(mappedBy = "residential")
    private List<Tropicano> residents = new ArrayList<>();

    // Getter e setter
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

	public List<Tropicano> getResidents() {
		return residents;
	}

	public void setResidents(List<Tropicano> residents) {
		this.residents = residents;
	}
    
    

    
}



/*
// Costruttore senza argomenti
public Residential() {}

// Costruttore con argomenti
public Residential(String name, double x1, double x2, double y1, double y2, int area, int capacity, int rent) {
    super(name, x1, x2, y1, y2, area);  // Passa 'area' al costruttore della superclasse
    this.capacity = capacity;
    this.rent = rent;
}
*/