package com.epic951.business.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epic951.business.services.ProductService;
import com.epic951.data.entities.Product;
import com.epic951.utilities.HTTPUtilities;

@RestController
@ResponseBody
@RequestMapping(value = "/api")
public class ProductController {

	private ResponseEntity<List<Product>> listResponse;
	private ResponseEntity<Product> singleResponse;

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/getproducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> body = productService.getAllProducts();
		listResponse = new ResponseEntity<List<Product>>(body, HttpStatus.OK);
		return listResponse;
	}

	@GetMapping(value = "/findproduct/{id}")
	public ResponseEntity<Product> findProductById(@PathVariable(value = "id") int id) {
		Product body = productService.findProductById(id);
		singleResponse = new ResponseEntity<Product>(body, HttpStatus.OK);
		return singleResponse;
	}

	@PostMapping(value = "/addproduct")
	public ResponseEntity<String> processAddProduct(@RequestBody Product p) {
		Product newProduct = productService.addOrUpdateProduct(p);
		if (newProduct != null) {
			return HTTPUtilities.handleResponse("Success");
		}
		return HTTPUtilities.handleResponse("Failure");
	}

	@Transactional
	@DeleteMapping(value = "/deleteproductbyname")
	public ResponseEntity<String> processDeleteProductByProductName(@RequestBody Product p) {
		if (productService.deleteProductByProductName(p.getProductName()) == 1) {
			return HTTPUtilities.handleResponse("Success");
		}
		return HTTPUtilities.handleResponse("Failure");
	}

	@Transactional
	@PutMapping(value = "/updateproduct")
	public ResponseEntity<String> processUpdateProduct(@RequestBody Product p) {
		if (productService.addOrUpdateProduct(p) != null) {
			return HTTPUtilities.handleResponse("Success");
		}
		return HTTPUtilities.handleResponse("Failure");
	}

}
