package com.generation.tropico.model.entities;

import jakarta.persistence.*;



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "building_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Building {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    
   

    
    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int setterId) {
        this.id = setterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String setterName) {
        this.name = setterName;
    }

	public String getAddress() {
		return address;
	}

	public void setAddress(String setterAddress) {
		this.address = setterAddress;
	}
    

}



/* 
private double x1;
private double x2;
private double y1;
private double y2;
private double area;
@ManyToOne
Isle isle;*/


/*
public double getX1() {
    return x1;
}

public void setX1(double x1) {
    this.x1 = x1;
}

public double getX2() {
    return x2;
}

public void setX2(double x2) {
    this.x2 = x2;
}

public double getY1() {
    return y1;
}

public void setY1(double y1) {
    this.y1 = y1;
}

public double getY2() {
    return y2;
}

public void setY2(double y2) {
    this.y2 = y2;
}

public double getArea() {
    return area;
}

public void setArea(double area) {
    this.area = area;
}
// Metodo astratto che le sottoclassi devono implementare
public abstract String buildingType();

public Isle getIsle() {
	return isle;
}

public void setIsle(Isle isle) {
	this.isle = isle;
}

*/

/*this.x1 = x1;
this.x2 = x2;
this.y1 = y1;
this.y2 = y2;
this.area = calculateArea();*/


/*
// Metodo per calcolare l'area
private double calculateArea() {
    return Math.abs((x2 - x1) * (y2 - y1));
}
*/


/*
// Costruttore senza argomenti
public Building() {}

// Costruttore con argomenti
public Building(String name, double x1, double x2, double y1, double y2, double area) {
    this.name = name;
   
}

*/