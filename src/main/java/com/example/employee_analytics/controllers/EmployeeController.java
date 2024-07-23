package com.example.employee_analytics.controllers;

import com.example.employee_analytics.dtos.response.CompanySalaryResponseDTO;
import com.example.employee_analytics.dtos.response.DepartmentAvgSalaryResponseDTO;
import com.example.employee_analytics.dtos.response.EmployeesResponseDTO;
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

    @GetMapping("/mostCommonFirstName")
    public MostCommonFirstNameDTO getMostCommonFirstName() {
        return service.getMostCommonFirstName();
    }

}
