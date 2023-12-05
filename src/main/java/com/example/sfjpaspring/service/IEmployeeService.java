package com.example.sfjpaspring.service;

import java.util.List;

import com.example.sfjpaspring.entity.Employee;

public interface IEmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deleteById(int id);

}
