package com.generation.tropico.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tropicano {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String name;
private String surname;
private int birthyear;
@Enumerated(EnumType.STRING)
Education education;
@Enumerated(EnumType.STRING)
Gender gender;
@Enumerated(EnumType.STRING)
Party party;
	
}
