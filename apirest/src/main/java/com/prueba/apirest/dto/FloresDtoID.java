package com.prueba.apirest.dto;

public class FloresDtoID {
	private int id;
	private String name;
	private double price;
	public FloresDtoID(int id2, String name2, double price2) {
		id=id2;
		name=name2;
		price=price2;
	}
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
