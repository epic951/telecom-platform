package com.epic951.business.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epic951.business.services.ProductService;
import com.epic951.data.entities.Product;

@RestController
@RequestMapping("/test")
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}
}
