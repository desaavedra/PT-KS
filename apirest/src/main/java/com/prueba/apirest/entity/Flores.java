package com.prueba.apirest.entity;

public class Flores {
	private int id;
	private String name;
	private double price;
	public Flores(String name2, double price2, int id2) {
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
	public String toString() {
        return "Flor [id=" + id + ", name=" + name + ", price=" + price +"]";
    }
}
