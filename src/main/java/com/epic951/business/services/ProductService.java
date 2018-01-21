package com.epic951.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epic951.data.entities.Product;
import com.epic951.data.repositories.ProductRepository;
import com.epic951.utilities.TestUtilities;

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
		// System.err.println("alreadyAdded " + alreadyAdded + " --- viableForUpdate " +
		// viableForUpdate + " values "
		// + p.getProductId() + " " + p.getProductName());
		if (!alreadyAdded && p.getProductName() != null && !p.getProductName().isEmpty()) {
			newProduct = productRepository.save(initializeProduct(p, "Create"));
			return newProduct;
		}
		if (viableForUpdate) {
			newProduct = productRepository.save(initializeProduct(p, "Update"));
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

	private Product initializeProduct(Product p, String status) {
		Product temp = null;
		if (status.toLowerCase().equals("update")) {
			temp = productRepository.findOne((long) p.getProductId());
		} else if (status.toLowerCase().equals("create")) {
			temp = TestUtilities.createTestProduct(0, null, "Default", 1, 1, 1);
		}
		temp = TestUtilities.createTestProduct(p.getProductId(), p.getProductName(),
				(p.getProductDescription() == null || p.getProductDescription().isEmpty() ? temp.getProductDescription()
						: p.getProductDescription()),
				(p.getMinPrice() <= 0 ? temp.getMinPrice() : p.getMinPrice()),
				(p.getMaxPrice() <= 0 ? temp.getMaxPrice() : p.getMaxPrice()),
				(p.getRating() <= 0 ? temp.getRating() : p.getRating()));
		return temp;
	}
}
