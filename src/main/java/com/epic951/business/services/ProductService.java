package com.epic951.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epic951.data.entities.Product;
import com.epic951.data.repositories.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ProductService() {
	}

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product addOrUpdateProduct(Product p) {
		// Validation is required ..
		Product newProduct = null;
		boolean alreadyAdded = productRepository.findByProductName(p.getProductName()).isPresent();
		boolean viableForUpdate = productRepository.findByProductId(p.getProductId()).isPresent();
		System.err.println("alreadyAdded " + alreadyAdded + " --- viableForUpdate " + viableForUpdate + " values "
				+ p.getProductId() + " " + p.getProductName());
		if (!alreadyAdded && p.getProductName() != null && !p.getProductName().isEmpty()) {
			newProduct = productRepository.save(p);
			System.err.println("Service add " + newProduct.toString());
			return newProduct;
		}
		if (viableForUpdate) {
			newProduct = productRepository.save(p);
			System.err.println("Service update " + newProduct.toString());
		}
		return newProduct;
	}

	public Product findProduct(Product product) {
		Product foundProduct = null;
		for (Product pr : productRepository.findAll()) {
			if (pr.getProductId() == product.getProductId()) {
				foundProduct = pr;
				break;
			}
		}
		System.err.println(foundProduct.toString());
		return foundProduct;
	}

	public Product findProductById(int id) {
		return productRepository.findByProductId(id).get();
	}

	public List<Product> getAllProducts() {

		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}

	public Integer deleteProductByProductName(String productName) {
		return productRepository.deleteByProductName(productName);
	}
}
