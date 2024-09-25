package com.generation.tropico.model.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Residential")
public class Residential extends Building implements Validable {

    private int capacity;
    private int rent;

    // Costruttore senza argomenti
    public Residential() {}

    // Costruttore con argomenti
    public Residential(String name, double x1, double x2, double y1, double y2, int area, int capacity, int rent) {
        super(name, x1, x2, y1, y2, area);  // Passa 'area' al costruttore della superclasse
        this.capacity = capacity;
        this.rent = rent;
    }

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

    @Override
    public boolean isValid() {
        // Validazione: L'area deve essere > 0, la capacità e l'affitto devono essere >= 0
        return super.getArea() > 0 && capacity >= 0 && rent >= 0;
    }

    @Override
    public String buildingType() {
        return "Residential";
    }
}
