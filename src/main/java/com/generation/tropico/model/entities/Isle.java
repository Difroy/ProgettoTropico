package com.generation.tropico.model.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Isle implements Validable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "isle")
    private List<Building> buildings = new ArrayList<>();

    // Costruttore senza argomenti
    public Isle() {}

    // Costruttore con argomenti
    public Isle(String name) {
        this.name = name;
    }

    // Getter e setter
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

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    // Validazione: l'isola è valida se ha un nome non nullo e non vuoto
    @JsonIgnore
    @Override
    public boolean isValid() {
        return name != null && !name.isBlank();
    }

    // Metodo per aggiungere un edificio all'isola
    public void addBuilding(Building building) {
        this.buildings.add(building);
        building.setIsle(this); // Imposta il riferimento inverso
    }
}
