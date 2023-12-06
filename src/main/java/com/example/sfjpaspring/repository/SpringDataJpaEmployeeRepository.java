package com.example.sfjpaspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.sfjpaspring.entity.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataJpaEmployeeRepository extends JpaRepository<Employee, Integer> {
    @Override
    @Transactional("readOnlyTransactionManager")
    List<Employee> findAll();

    @Override
    @Transactional("readOnlyTransactionManager")
    Optional<Employee> findById(Integer id);
}
