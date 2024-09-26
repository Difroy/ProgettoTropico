package com.generation.tropico.model.dto;

public class PairDTO {

	String value;
	String label;
	
	
	public PairDTO(String v, String l)
	{
		this.value = v;
		this.label = l;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}
	
	
	
}
