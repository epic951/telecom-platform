package com.epic951.data.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.epic951.data.entities.Product;

@Repository
@RepositoryRestResource(exported = false)
public interface ProductRepository extends CrudRepository<Product, Long> {
	public Optional<Product> findByProductName(String productName);

	public Optional<Product> findByProductId(int productId);

	public Integer deleteByProductName(String productName);

	public Integer deleteByProductId(int productId);
}
