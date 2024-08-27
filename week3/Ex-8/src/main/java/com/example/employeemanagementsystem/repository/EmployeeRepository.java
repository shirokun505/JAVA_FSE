package com.example.employeemanagementsystem.repository;
import com.example.employeemanagementsystem.projection.EmpNameEmail;
import com.example.employeemanagementsystem.projection.EmpDetails;
import com.example.employeemanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(name = "Employee.findByDepartmentName")
    List<Employee> findEmployeesByDepartmentName(String departmentName);


    //Implementing Pagination and sorting
    Page<Employee> findByDepartmentId(Long departmentId, Pageable pageable);

    // Finding employees through pagination and sorting
    Page<Employee> findByNameContaining(String name, Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.department.id = :deptId")
    List<EmpNameEmail> findByDeptId(Long deptId);

    @Query("SELECT new com.example.employeemanagementsystem.projection.EmpDetails(e.name, e.email, d.name) " +
            "FROM Employee e JOIN e.department d WHERE d.id = :deptId")
    List<EmpDetails> findEmpDetailsByDeptId(Long deptId);


}
