package com.fdmgroup.CartApiSopuluchukwuNwakaeze.service;

import java.util.List;

import com.fdmgroup.CartApiSopuluchukwuNwakaeze.model.Product;

public interface ProductClient {

	public List<Product> getProducts();

	public Product getProduct(long id);

	public Product postProduct(Product product);

	public void putProduct(Product product);

	public void deleteProduct(long id);
}
