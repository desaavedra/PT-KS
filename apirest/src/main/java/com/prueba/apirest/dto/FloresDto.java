package com.prueba.apirest.dto;


public class FloresDto {
	
	private String name;
	private double price;
	public FloresDto(String name2, double price2) {
		name=name2;
		price=price2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
