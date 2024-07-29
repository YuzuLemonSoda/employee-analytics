package com.example.employee_analytics.controllers;

import com.example.employee_analytics.dtos.response.*;
import com.example.employee_analytics.service.EmployeesService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/analytics")
public class EmployeeController {

    private final EmployeesService service;

    @GetMapping("/employeesWithFirstName")
    public List<EmployeesResponseDTO> getEmployeesWithFirstName(@NotNull @Valid String firstName) {
        return service.getEmployeesWithFirstName(firstName);
    }

    @GetMapping("/companyAverageSalary")
    public CompanySalaryResponseDTO getCompanyAverageSalary() {
        return service.getCompanyAverageSalary();
    }

    @GetMapping("/departmentAverageSalary")
    public List<DepartmentAvgSalaryResponseDTO> getDepartmentAverageSalary() {
        return service.getDepartmentAverageSalary();
    }

    @GetMapping("/employeesByDepartment")
    public List<EmployeesByDeptResponseDTO> getEmployeesByDepartment() {
        return service.getEmployeesByDepartment();
    }

    @GetMapping("/departmentAnnualPayroll")
    public List<DepartmentAnnualPayrollResponseDTO> getDepartmentAnnualPayroll() {
        return service.getDepartmentAnnualPayroll();
    }

    @GetMapping("/medianSalaryByDepartment")
    public List<MedianSalaryByDeptResponseDTO> getMedianSalaryByDepartment() {
        return service.getMedianSalaryByDepartment();
    }

    @GetMapping("/firstEmployeesToJoin")
    public List<FirstEmployeesToJoinResponseDTO> getFirstEmployeesToJoin() {
        return service.getFirstEmployeesToJoin();
    }

}
