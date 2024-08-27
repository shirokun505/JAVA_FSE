package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.entity.Employee;
import com.example.employeemanagementsystem.projection.EmpDetails;
import com.example.employeemanagementsystem.projection.EmpNameEmail;
import com.example.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    //Creating new employee
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        try {
            Employee empObj = employeeRepository.save(employee);
            return new ResponseEntity<>(empObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Printing all employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = employeeRepository.findAll();
            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Getting an employee with desired id
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Updating an employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            Employee empObj = employee.get();
            empObj.setName(employeeDetails.getName());
            empObj.setEmail(employeeDetails.getEmail());
            empObj.setDepartment(employeeDetails.getDepartment());
            return new ResponseEntity<>(employeeRepository.save(empObj), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Deleting an employee using Id
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {
        try {
            employeeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //To get employee working at a certain department
    @GetMapping("/department/{departmentName}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentName(@PathVariable String departmentName) {
        List<Employee> employees = employeeRepository.findEmployeesByDepartmentName(departmentName);
        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employees);
    }
    //Getting employees through Pagination and Sorting
    @GetMapping("/department/{departmentId}")
    public Page<Employee> getEmployeesByDepartment(
            @PathVariable Long departmentId,
            Pageable pageable) {
        return employeeRepository.findByDepartmentId(departmentId, pageable);
    }

    //Getting employee by name with pagination and sorting
    @GetMapping("/name")
    public Page<Employee> getEmployeesByName(
            @RequestParam String name,
            Pageable pageable) {
        return employeeRepository.findByNameContaining(name, pageable);
    }

    @GetMapping("/dept/{deptId}/simple")
    public ResponseEntity<List<EmpNameEmail>> getSimpleEmpDataByDept(@PathVariable Long deptId) {
        List<EmpNameEmail> employees = employeeRepository.findByDeptId(deptId);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/dept/{deptId}/details")
    public ResponseEntity<List<EmpDetails>> getDetailedEmpDataByDept(@PathVariable Long deptId) {
        List<EmpDetails> employees = employeeRepository.findEmpDetailsByDeptId(deptId);
        return ResponseEntity.ok(employees);
    }

}
