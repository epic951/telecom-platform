package com.epic951.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epic951.data.entities.Product;
import com.epic951.data.repositories.ProductRepository;
import com.epic951.utilities.HTTPUtilities;
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
		if (!alreadyAdded) {
			newProduct = validateParameters(p, "create");
			return newProduct;
		}
		if (viableForUpdate) {
			newProduct = validateParameters(p, "update");
		}
		setValidationErrors(p, "empty");
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

	public Integer deleteProduct(Product p) {
		if (!compareStrings(p.getProductName(), null)) {
			return productRepository.deleteByProductName(p.getProductName());
		} else if (p.getProductId() > 0) {
			return productRepository.deleteByProductId(p.getProductId());
		}
		setValidationErrors(p, "delete");
		return 0;
	}

	private Product initializeProduct(Product p, String status) {
		Product temp = null;
		if (status.toLowerCase().equals("update")) {
			temp = productRepository.findByProductId(p.getProductId()).get();
		} else if (status.toLowerCase().equals("create")) {
			temp = TestUtilities.createTestProduct(0, "default-product-name", "default-product-description", 1, 1,
					"https://openclipart.org/download/85345/home-sim-card.svg", 1);
		}
		temp = TestUtilities.createTestProduct(p.getProductId(),
				(compareStrings(p.getProductName(), null) ? temp.getProductName() : p.getProductName()),
				(compareStrings(p.getProductDescription(), null) ? temp.getProductDescription()
						: p.getProductDescription()),
				(p.getMinPrice() <= 0 ? temp.getMinPrice() : p.getMinPrice()),
				(p.getMaxPrice() <= 0 ? temp.getMaxPrice() : p.getMaxPrice()),
				(compareStrings(p.getImageUrl(), null) || p.getImageUrl().equals("") ? temp.getImageUrl()
						: p.getImageUrl()),
				(p.getRating() <= 0 ? temp.getRating() : p.getRating()));
		return temp;
	}

	private boolean compareStrings(String str1, String str2) {
		return (str1 == null ? str2 == null : str1.equals(str2));
	}

	private Product validateParameters(Product p, String status) {
		Product temp = null;
		if (compareStrings(p.getProductName(), null)) {
			setValidationErrors(p, "empty");
		} else {
			temp = productRepository.save(initializeProduct(p, status));
		}
		return temp;
	}

	private void setValidationErrors(Product p, String field) {
		switch (field.toLowerCase()) {
		case "empty":
			String temp = new HTTPUtilities().getErrors().get(0);
			if (temp.contains("#Service") || temp.contains("#Operator")) {
				HTTPUtilities.setErrors(new ArrayList<>());
			}
			if (compareStrings(p.getProductName(), null)) {
				HTTPUtilities.setErrors("#Product Product Name can not be empty or null");
				HTTPUtilities.setErrorMessage("Product name is a required field and can not be empty");
			}
			if (p.getProductId() < 0) {
				HTTPUtilities.setErrors("#Product Product ID can not be empty or null");
				HTTPUtilities.setErrorMessage("Product ID is required to perform updating");
			}
			break;
		case "delete":
			HTTPUtilities.setErrors(new ArrayList<>());
			HTTPUtilities.setErrors("Product ID & Product Name can not both be empty or null");
			HTTPUtilities.setErrorMessage("Either Product ID or Product Name is required to perform deletion");
			break;
		default:
			break;
		}

	}

}
