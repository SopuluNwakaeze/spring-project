package com.fdmgroup.CartApiSopuluchukwuNwakaeze.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("unused")
@Entity
@Table(name = "carts")
public class Cart {

	@Id
//	@Column(name = "Cart_Id")
	@GeneratedValue
	private long id;
	@ElementCollection
	private List<Long> productsId;
	@Column(name = "Total_Price")
	private double totalPrice;

	public Cart() {
		totalPrice = 0.0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Long> getProductsId() {
		return productsId;
	}

	public void addProduct(Long productId) {
		productsId.add(productId);
	}

	public void removeProduct(Long productId) {
		productsId.remove(productId);
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void addTotalPrice(double price) {
		double counter = totalPrice;
		counter += price;
		totalPrice = counter;
	}

	public void removeFromTotalPrice(double price) {
		double counter = totalPrice;
		counter -= price;
		totalPrice = counter;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", productsId=" + productsId + ", totalPrice=" + totalPrice + "]";
	}

	public void removeAll() {
		productsId.clear();

	}

	public void setTotalPrice(double price) {
		totalPrice = price;

	}

}
