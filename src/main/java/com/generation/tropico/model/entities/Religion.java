package com.generation.tropico.model.entities;

public enum Religion {

	NONE("Doesn't answer / No religion"),
	CTHULLHU("Our lord and Saviour Cthullhu and the Elder Gods of the latter days"),
	TROPICANESIMO("El prez, El Salvador, El triunfo de nuestra nazione"),
	METALLARISMO("There is only one Dio, Ronny James");
	
	
String label;
	
	Religion(String l)
	{
		this.label = l;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
