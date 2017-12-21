package com.epic951.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.epic951.data.entities.Operator;

public interface OperatorRepository extends CrudRepository<Operator, Long> {
	public Operator findByOperatorName(String operatorName);
}
