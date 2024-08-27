package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Derived query method to find employees by department id
    List<Employee> findByDepartmentId(Long departmentId);

    // Derived query method to find employees by name containing a specific keyword
    List<Employee> findByNameContaining(String keyword);
}
