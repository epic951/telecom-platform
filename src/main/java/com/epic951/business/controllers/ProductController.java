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

@RestController
@ResponseBody
@RequestMapping(value = "/api")
public class ProductController {

	private ResponseEntity<String> response;

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/getproducts")
	public ResponseEntity<String> getAllProducts() {
		List<Product> body = productService.getAllProducts();
		response = new ResponseEntity<String>(body.toString(), HttpStatus.OK);
		return response;
	}

	@GetMapping(value = "/findproduct/{id}")
	public ResponseEntity<String> findProductById(@PathVariable(value = "id") int id) {
		Product body = productService.findProductById(id);
		response = new ResponseEntity<String>(body.toString(), HttpStatus.OK);
		return response;
	}

	@PostMapping(value = "/addproduct")
	public ResponseEntity<String> processAddProduct(@RequestBody Product p) {
		Product newProduct = productService.addOrUpdateProduct(p);
		if (newProduct != null) {
			response = new ResponseEntity<String>("Success", HttpStatus.OK);
			return response;
		}
		response = new ResponseEntity<String>("Failure", HttpStatus.BAD_REQUEST);
		return response;
	}

	@Transactional
	@DeleteMapping(value = "/deleteproductbyname")
	public ResponseEntity<String> processDeleteProductByProductName(@RequestBody Product p) {
		if (productService.deleteProductByProductName(p.getProductName()) == 1) {
			response = new ResponseEntity<String>("Success", HttpStatus.OK);
			return response;
		}
		response = new ResponseEntity<String>("Failure", HttpStatus.BAD_REQUEST);
		return response;
	}

	@Transactional
	@PutMapping(value = "/updateproduct")
	public ResponseEntity<String> processUpdateProduct(@RequestBody Product p) {
		if (productService.addOrUpdateProduct(p) != null) {
			response = new ResponseEntity<String>("Success", HttpStatus.OK);
			return response;
		}
		response = new ResponseEntity<String>("Failure", HttpStatus.BAD_REQUEST);
		return response;
	}

}
