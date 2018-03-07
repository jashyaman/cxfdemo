package com.keblal.cxfdemo.models;

public class Place {
	
	private String city;
	private String country;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Place(String city, String country) {
		super();
		this.city = city;
		this.country = country;
	}
	
	

}
