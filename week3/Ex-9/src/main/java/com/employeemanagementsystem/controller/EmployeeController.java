package com.employeemanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagementsystem.dto.EmployeeDTO;
import com.employeemanagementsystem.entity.Employee;
import com.employeemanagementsystem.projection.EmployeeProjection;
import com.employeemanagementsystem.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //Calling Desired Employee with id
    @GetMapping("/employees")
    public EmployeeProjection getEmpById(@PathVariable Long id) {
        return employeeService.getEmpById(id);
    }

    //Calling Desired employee by name
    @GetMapping("/employees/{name}")
    public List<EmployeeProjection> getEmployeeByName(@PathVariable String name) {
        return employeeService.findByName(name);
    }

    //Calling Desired employee by their email
    @GetMapping("/employees/{email}")
    public List<EmployeeProjection> getEmployeeByEmail(@PathVariable String email) {
        return employeeService.findByEmail(email);
    }

    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    //Creating a new employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    //Updating existing employee
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            employee.setName(employeeDetails.getName());
            employee.setEmail(employeeDetails.getEmail());
            employee.setDepartment(employeeDetails.getDepartment());
            return employeeService.saveEmployee(employee);
        }
        return null;
    }
    //Deleting existing employee
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            employeeService.deleteEmployee(id);
        }
    }
}
