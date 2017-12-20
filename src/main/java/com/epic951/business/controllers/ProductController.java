package com.epic951.business.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epic951.business.services.ProductService;
import com.epic951.data.entities.Product;

@RestController
@RequestMapping("/test")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/products")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@PostMapping(value = "/addproduct")
	public String processAddProduct(@RequestBody Product p) {
		Product newProduct = productService.addProduct(p);
		if (newProduct != null) {
			return "success";
		}
		return "failure";
	}
}
