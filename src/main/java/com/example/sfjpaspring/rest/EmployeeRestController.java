package com.example.sfjpaspring.rest;

import java.util.List;

import com.example.sfjpaspring.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.sfjpaspring.entity.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private final IEmployeeService employeeService;

    @Autowired
    public EmployeeRestController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        return employeeService.findById(employeeId);
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        if (employeeService.findById(employeeId) == null) {
            return "Employee id not found - " + employeeId;
        }
        employeeService.deleteById(employeeId);
        return "Deleted employee id - " + employeeId;
    }
}