package com.example.employee_analytics.service;

import com.example.employee_analytics.dtos.response.*;

import java.util.List;


public interface EmployeesService {

    List<EmployeesResponseDTO> getEmployeesWithFirstName(String firstName);

    CompanySalaryResponseDTO getCompanyAverageSalary();

    List<DepartmentAvgSalaryResponseDTO> getDepartmentAverageSalary();

    List<EmployeesByDeptResponseDTO> getEmployeesByDepartment();

    List<DepartmentAnnualPayrollResponseDTO> getDepartmentAnnualPayroll();

}
