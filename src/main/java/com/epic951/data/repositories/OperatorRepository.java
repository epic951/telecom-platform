package com.epic951.data.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.epic951.data.entities.Operator;

public interface OperatorRepository extends CrudRepository<Operator, Long> {
	public Optional<Operator> findByOperatorName(String operatorName);

	public Integer deleteByOperatorName(String operatorName);

	public Optional<Operator> findByOperatorId(int operatorId);
}
