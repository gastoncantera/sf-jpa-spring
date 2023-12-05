package com.example.sfjpaspring.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.sfjpaspring.entity.Employee;

@Repository
public class JpaEmployeeRepository {
    private EntityManager entityManager;

    @Autowired
    public JpaEmployeeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Employee> findAll() {
        return entityManager.createQuery("from Employee").getResultList();
    }

    public Employee findById(int id) {
        return entityManager.find(Employee.class, id);
    }

    public Employee save(Employee employee) {
        return entityManager.merge(employee);
    }

    public void deleteById(int id) {
        Query query = entityManager.createQuery("delete from Employee where id=:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
