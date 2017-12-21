package com.epic951.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epic951.data.entities.Product;
import com.epic951.data.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product addProduct(Product p) {

		// Validation is required ..
		if (p.getProduct_name() != null && !p.getProduct_name().isEmpty()) {
			Product newProduct = productRepository.save(p);
			return newProduct;
		}
		return null;
	}

	public List<Product> getAllProducts() {

		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}
}
