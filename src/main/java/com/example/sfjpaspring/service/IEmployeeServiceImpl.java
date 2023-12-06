package com.example.sfjpaspring.service;

import java.util.List;

import com.example.sfjpaspring.entity.Employee;
import com.example.sfjpaspring.repository.JpaEmployeeRepository;
import com.example.sfjpaspring.repository.SpringDataJpaEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IEmployeeServiceImpl implements IEmployeeService {

//    @Autowired
//    private SpringDataJpaEmployeeRepository employeeRepository;

    @Autowired
    private JpaEmployeeRepository employeeRepository;

    @Override
    @Transactional
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional
    public Employee findById(int id) {
        return this.findAll()
                .stream()
                .filter(e -> e.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}



