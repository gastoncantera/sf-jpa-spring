package com.example.sfjpaspring.service;

import com.example.sfjpaspring.entity.Employee;
import com.example.sfjpaspring.repository.JpaEmployeeRepository;
import com.example.sfjpaspring.repository.SpringDataJpaEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IEmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private SpringDataJpaEmployeeRepository employeeRepository;

//    @Autowired
//    private JpaEmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id).isPresent() ? employeeRepository.findById(id).get() : null;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}



