package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepoitory extends JpaRepository<Department, Long> {
    Department findByName(String name);
}
