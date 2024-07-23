package com.example.employee_analytics.service;

import com.example.employee_analytics.dtos.response.CompanySalaryResponseDTO;
import com.example.employee_analytics.dtos.response.DepartmentAvgSalaryResponseDTO;
import com.example.employee_analytics.dtos.response.EmployeesResponseDTO;

import java.util.List;


public interface EmployeesService {

    List<EmployeesResponseDTO> getEmployeesWithFirstName(String firstName);

    CompanySalaryResponseDTO getCompanyAverageSalary();

    List<DepartmentAvgSalaryResponseDTO> getDepartmentAverageSalary();

    MostCommonFirstNameDTO getMostCommonFirstName();
}
