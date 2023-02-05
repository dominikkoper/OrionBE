package com.dkoper.orion.repository;


import com.dkoper.orion.model.Comparison;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ComparisonRepository extends CrudRepository<Comparison, Integer> {

    Optional<Comparison> findComparisonById(int id);
    void deleteByCreatedOnBefore(LocalDateTime localDateTime);
}
