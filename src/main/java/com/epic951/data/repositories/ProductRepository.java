package com.epic951.data.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epic951.data.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	public Optional<Product> findByProductName(String productName);

	public Optional<Product> findByProductId(int productId);

	public Integer deleteByProductName(String productName);
}
