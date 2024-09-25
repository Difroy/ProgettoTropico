package com.generation.tropico.model.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tropicano implements Validable {
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
	int satisfaction;

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getBirthyear() {
		return birthyear;
	}

	public void setBirthyear(int birthyear) {
		this.birthyear = birthyear;
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}
	
	public int getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(int satisfaction) {
		this.satisfaction = satisfaction;
	}

	@Override
	public boolean isValid() {
		// Controllo che nome e cognome non siano nulli o vuoti
		if (this.name == null || this.name.trim().isEmpty()) {
			return false;
		}
		if (this.surname == null || this.surname.trim().isEmpty()) {
			return false;
		}

		// Controllo che l'anno di nascita sia tra 1980 e 2015
		if (this.birthyear < 1980 || this.birthyear > 2015) {
			return false;
		}

		// Controllo che education, gender e party non siano null
		if (this.education == null || this.gender == null || this.party == null) {
			return false;
		}

		// Se tutti i controlli passano, l'oggetto è valido
		return true;
	}

	public List<String> getErrors() {
		List<String> errors = new ArrayList<>();
		if (this.name == null || this.name.trim().isEmpty()) {
			errors.add("Name cannot be empty");
		}
		if (this.surname == null || this.surname.trim().isEmpty()) {
			errors.add("Surname cannot be empty");
		}
		if (this.birthyear < 1980 || this.birthyear > 2015) {
			errors.add("Birthyear must be between 1980 and 2015");
		}
		if (this.education == null) {
			errors.add("Education cannot be empty");
		}
		if (this.satisfaction <= 0) {
			errors.add("Satisfaction cannot be empty or negative");
		}
		return errors;
	}
	
}