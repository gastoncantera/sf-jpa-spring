package com.example.sfjpaspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.sfjpaspring.entity.Employee;

public interface SpringDataJpaEmployeeRepository extends JpaRepository<Employee, Integer> {
}
