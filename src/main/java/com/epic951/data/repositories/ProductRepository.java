package com.epic951.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epic951.data.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

}
