package com.generation.tropico.model.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Industry")
public class Industry extends Building implements Validable {

    private String product;
    private int quantity;
    private int jobs;

    // Costruttore senza argomenti
    public Industry() {}

    // Costruttore con argomenti
    public Industry(String name, double x1, double x2, double y1, double y2, int area, String product, int quantity, int jobs) {
        super(name, x1, x2, y1, y2, area);  // Passa 'area' al costruttore della superclasse
        this.product = product;
        this.quantity = quantity;
        this.jobs = jobs;
    }

    // Getter e setter
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getJobs() {
        return jobs;
    }

    public void setJobs(int jobs) {
        this.jobs = jobs;
    }

    @Override
    public boolean isValid() {
        return super.getArea() > 0 && jobs >= 0 && product != null && quantity >= 0;
    }

    @Override
    public String buildingType() {
        return "Industry";
    }
}

