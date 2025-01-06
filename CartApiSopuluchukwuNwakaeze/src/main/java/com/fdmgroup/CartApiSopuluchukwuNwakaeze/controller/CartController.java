package com.fdmgroup.CartApiSopuluchukwuNwakaeze.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.CartApiSopuluchukwuNwakaeze.model.Cart;
import com.fdmgroup.CartApiSopuluchukwuNwakaeze.service.CartService;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

	private CartService cartService;

	@Autowired
	public CartController(CartService cartService) {
		super();
		this.cartService = cartService;
	}

	@GetMapping
	public List<Cart> getCarts() {
		return cartService.getCarts();
	}

	@GetMapping("/{id}")
	public Cart getCartById(@PathVariable("id") long id) {
		return cartService.getCartsById(id);
	}

	@PostMapping("/{cartId}/add/{productId}")
	public Cart addProduct(@PathVariable("cartId") long cartId, @PathVariable("productId") long productId) {
		return cartService.addProduct(cartId, productId);
	}

	@PostMapping
	public ResponseEntity<Cart> createCart(@Valid @RequestBody Cart cart) {
		Cart createdCart = cartService.createCart(cart);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdCart.getId()).toUri();
		return ResponseEntity.created(location).body(createdCart);
	}
//
//	@PostMapping("/{cartId}/add/{productId}")
//	public ResponseEntity<Cart> addProduct(@PathVariable("cartId") long cartId,
//			@PathVariable("productId") long productId) {
//		Cart newCart = cartService.addProduct(cartId, productId);
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cartId}/add/{productId}")
//				.buildAndExpand(cartId, productId).toUri();
//		return ResponseEntity.created(location).body(newCart);
//
//	}

	@DeleteMapping("/{cartId}/remove/{productId}")
	public void deleteProduct(@PathVariable("cartId") long cartId, @PathVariable("productId") long productId) {
		cartService.deleteProduct(cartId, productId);
	}

	@DeleteMapping("/clear/{cartId}")
	public void deleteAll(@PathVariable("cartId") long cartId) {
		cartService.deleteAllProducts(cartId);
	}

	@DeleteMapping("/delete-cart/{cartId}")
	public void deleteCart(@PathVariable("cartId") long cartId) {
		cartService.deleteCart(cartId);
	}

}
