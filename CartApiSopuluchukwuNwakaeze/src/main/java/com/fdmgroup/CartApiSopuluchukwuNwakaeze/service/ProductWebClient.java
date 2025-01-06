package com.fdmgroup.CartApiSopuluchukwuNwakaeze.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fdmgroup.CartApiSopuluchukwuNwakaeze.model.Product;

import reactor.core.publisher.Mono;

@Service
public class ProductWebClient implements ProductClient {

	private WebClient webClient;

	@Autowired
	public ProductWebClient(WebClient webClient) {
		super();
		this.webClient = webClient;
	}

	@Override
	public List<Product> getProducts() {
		return webClient.get().uri(builder -> builder.path("/api/v1/products/").build()).retrieve()
				.bodyToFlux(Product.class).collectList().block();
	}

	@Override
	public Product getProduct(long id) {
		return webClient.get().uri(builder -> builder.path("/api/v1/products/{id}").build(id)).retrieve()
				.onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value(),
						response -> Mono
								.error(new ProductNotFoundException("Product With ID " + id + " Was Not Found.")))
				.bodyToMono(Product.class).block();
	}

	@Override
	public Product postProduct(Product product) {
		return webClient.post().uri(builder -> builder.path("/api/v1/products").build()).bodyValue(product).retrieve()
				.bodyToMono(Product.class).block();
	}

	@Override
	public void putProduct(Product product) {
		webClient.put().uri(builder -> builder.path("/api/v1/products/{id}").build(product.getId())).bodyValue(product)
				.retrieve().toBodilessEntity().block();

	}

	@Override
	public void deleteProduct(long id) {
		webClient.delete().uri(builder -> builder.path("/api/v1/products/{id}").build(id)).retrieve().toBodilessEntity()
				.block();

	}

}
