package com.epic951.data.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.epic951.data.entities.Operator;

@Repository
@RepositoryRestResource(exported = false)
public interface OperatorRepository extends CrudRepository<Operator, Long> {
	public Optional<Operator> findByOperatorName(String operatorName);

	public Integer deleteByOperatorName(String operatorName);

	public Optional<Operator> findByOperatorId(int operatorId);

	public Integer deleteByOperatorId(int oeratorId);
}
