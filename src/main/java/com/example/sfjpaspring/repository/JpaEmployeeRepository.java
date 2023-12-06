package com.example.sfjpaspring.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.example.sfjpaspring.entity.Employee;

@Repository
public class JpaEmployeeRepository {
    private EntityManager entityManager;

    @Autowired
    @Qualifier("readOnlyEntityManagerFactory")
    private EntityManager readOnlyEntityManager;

    @Autowired
    public JpaEmployeeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Employee> findAll() {
        return readOnlyEntityManager.createQuery("from Employee").getResultList();
    }

    public Optional<Employee> findById(int id) {
        return Optional.ofNullable(readOnlyEntityManager.find(Employee.class, id));
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
