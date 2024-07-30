package com.example.employee_analytics.service.impl;

import com.example.employee_analytics.dtos.response.*;
import com.example.employee_analytics.models.entities.Employees;
import com.example.employee_analytics.repository.EmployeeRepository;
import com.example.employee_analytics.service.EmployeesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeesServiceImpl implements EmployeesService {

    private final EmployeeRepository repository;

    @Override
    public List<EmployeesResponseDTO> getEmployeesWithFirstName(String firstName) {

        Optional<List<Employees>> optionalEmployeesList = repository.findAllByFirstName(firstName);

        if(optionalEmployeesList.isEmpty() || optionalEmployeesList.get().isEmpty()) {
            throw new RuntimeException("There are no Employee's by the name of " + firstName + " at our company");
        }

        return optionalEmployeesList.get().stream()
                .map(employee -> new EmployeesResponseDTO(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getDepartmentId()))
                .collect(Collectors.toList());

    }

    @Override
    public CompanySalaryResponseDTO getCompanyAverageSalary() {

        BigDecimal average = repository.findAverageSalary();

        return new CompanySalaryResponseDTO(average);
    }

    @Override
    public List<DepartmentAvgSalaryResponseDTO> getDepartmentAverageSalary() {

        return repository.findDepartmentAverageSalaries();

    }

    @Override
    public List<EmployeesByDeptResponseDTO> getEmployeesByDepartment() {

        return repository.findEmployeesByDepartment();

    }

    @Override
    public List<DepartmentAnnualPayrollResponseDTO> getDepartmentAnnualPayroll() {

        return repository.findDepartmentAnnualSalaries();

    }

    @Override
    public List<MedianSalaryByDeptResponseDTO> getMedianSalaryByDepartment() {
        return repository.findMedianSalaryByDepartment();
    }

    @Override
    public List<FirstEmployeesToJoinResponseDTO> getFirstEmployeesToJoin() {
        return repository.findFirstEmployeesToJoin();
    }


}
