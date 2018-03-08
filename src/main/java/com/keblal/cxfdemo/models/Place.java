package com.keblal.cxfdemo.models;

import java.util.List;

public class Place {
	
	private String countryName;
	
	private List<String> states;

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public List<String> getStates() {
		return states;
	}

	public void setStates(List<String> states) {
		this.states = states;
	}

	public Place(String countryName, List<String> states) {
		super();
		this.countryName = countryName;
		this.states = states;
	}

	public Place() {
		// TODO Auto-generated constructor stub
	}
	
	

}
