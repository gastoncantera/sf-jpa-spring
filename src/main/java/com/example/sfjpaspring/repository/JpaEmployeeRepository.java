package com.example.sfjpaspring.repository;

import com.example.sfjpaspring.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaEmployeeRepository {
    private EntityManager entityManager;

    @Autowired
    public JpaEmployeeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return entityManager.createQuery("from Employee").getResultList();
    }

    @Transactional(readOnly = true)
    public Optional<Employee> findById(int id) {
        return Optional.ofNullable(entityManager.find(Employee.class, id));
    }

    @Transactional
    public Employee save(Employee employee) {
        return entityManager.merge(employee);
    }

    @Transactional
    public void deleteById(int id) {
        Query query = entityManager.createQuery("delete from Employee where id=:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
