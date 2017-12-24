package com.epic951.business.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epic951.business.services.ProductService;
import com.epic951.data.entities.Product;

@RestController
@ResponseBody
@RequestMapping(value = "/api")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/getproducts")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@PostMapping(value = "/addproduct")
	public String processAddProduct(@RequestBody Product p) {
		Product newProduct = productService.addOrUpdateProduct(p);
		if (newProduct != null) {
			return "success";
		}
		return "failure";
	}

	@Transactional
	@DeleteMapping(value = "/deleteproductbyname")
	public String processDeleteProductByProductName(@RequestBody Product p) {
		if (productService.deleteProductByProductName(p.getProductName()) == 1) {
			return "success";
		}
		return "failure";
	}

	@Transactional
	@PutMapping(value = "/updateproduct")
	public String processUpdateProduct(@RequestBody Product p) {
		if (productService.addOrUpdateProduct(p) != null) {
			return "success";
		}
		return "failure";
	}

}
