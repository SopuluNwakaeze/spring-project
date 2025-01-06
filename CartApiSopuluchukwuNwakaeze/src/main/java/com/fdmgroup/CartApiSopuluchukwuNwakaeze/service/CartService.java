package com.fdmgroup.CartApiSopuluchukwuNwakaeze.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.CartApiSopuluchukwuNwakaeze.model.Cart;
import com.fdmgroup.CartApiSopuluchukwuNwakaeze.model.Product;
import com.fdmgroup.CartApiSopuluchukwuNwakaeze.repository.CartRepository;

@Service
public class CartService {

	private CartRepository cartRepo;
	private ProductClient productClient;

	@Autowired
	public CartService(CartRepository cartRepo, ProductClient productClient) {
		super();
		this.cartRepo = cartRepo;
		this.productClient = productClient;

	}

	public List<Cart> getCarts() {
		return cartRepo.findAll();
	}

	public Cart addProduct(long cartId, long productId) {
		System.out.println("CHECK@@@@@@@");

		Product product = productClient.getProduct(productId);
		Optional<Cart> opCart = cartRepo.findById(cartId);
		if (!opCart.isPresent()) {
			throw new CartNotFoundException(
					"Add Operation Could Not Be Perfomred Because Cart with Id " + cartId + " Could Not Be Found.");
		}
		Cart cart = opCart.get();
		cart.addProduct(productId);
		cart.addTotalPrice(product.getPrice());
		System.out.println("PRICE: " + cart.getTotalPrice());
		cartRepo.save(cart);

		return cart;
	}

	public Cart createCart(@Valid Cart cart) {
		return cartRepo.save(cart);
	}

	public Cart getCartsById(long cartId) {
		Optional<Cart> opCart = cartRepo.findById(cartId);
		if (!opCart.isPresent()) {
			throw new CartNotFoundException("Get Cart Operation Could Not Be Perfomred Because Cart with Id " + cartId
					+ " Could Not Be Found.");
		}
		Cart cart = opCart.get();
		return cart;
	}

	public void deleteProduct(long cartId, long productId) {
		Product product = productClient.getProduct(productId);
		Optional<Cart> opCart = cartRepo.findById(cartId);
		if (!opCart.isPresent()) {
			throw new CartNotFoundException(
					"Delete Operation Could Not Be Performed Because Cart with Id " + cartId + " Could Not Be Found.");
		}
		Cart cart = opCart.get();
		cart.removeProduct(productId);
		cart.removeFromTotalPrice(product.getPrice());
		cartRepo.save(cart);

	}

	public void deleteAllProducts(long cartId) {
		Optional<Cart> opCart = cartRepo.findById(cartId);
		if (!opCart.isPresent()) {
			throw new CartNotFoundException("Delete All Operation Could Not Be Perfomred Because Cart with Id " + cartId
					+ " Could Not Be Found.");
		}
		Cart cart = opCart.get();
		cart.removeAll();
		cart.setTotalPrice(0.0);
		cartRepo.save(cart);

	}

	public void deleteCart(long cartId) {
		Optional<Cart> opCart = cartRepo.findById(cartId);
		if (!opCart.isPresent()) {
			throw new CartNotFoundException("Delete Cart Operation Could Not Be Perfomred Because Cart with Id "
					+ cartId + " Could Not Be Found.");
		}
		Cart cart = opCart.get();
		cart.getProductsId().clear();
		cartRepo.delete(cart);
	}

}
