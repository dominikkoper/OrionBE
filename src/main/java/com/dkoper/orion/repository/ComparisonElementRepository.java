package com.dkoper.orion.repository;

import com.dkoper.orion.model.ComparisonElement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComparisonElementRepository extends CrudRepository<ComparisonElement, Integer> {
}
