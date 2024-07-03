package com.example.employee_analytics.service.impl;

import com.example.employee_analytics.dtos.response.EmployeesResponseDTO;
import com.example.employee_analytics.models.entities.Employees;
import com.example.employee_analytics.repository.EmployeeRepository;
import com.example.employee_analytics.service.EmployeesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
