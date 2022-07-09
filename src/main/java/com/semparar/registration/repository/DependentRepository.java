package com.semparar.registration.repository;

import com.semparar.registration.model.Dependent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DependentRepository extends JpaRepository<Dependent, Long> {
}
