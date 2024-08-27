package com.example.employeemanagementsystem.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmpDetails {
    private final String name;
    private final String email;
    private final String deptName;

}
