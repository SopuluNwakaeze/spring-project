package com.fdmgroup.CartApiSopuluchukwuNwakaeze.model;

public class Product {

	private long id;
	@NotBlank(message = "Product Name Cannot Be Blank")
	private String name;
	@NotNull(message = "Price Cannot Be Blank")
	private double price;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
	}

}
