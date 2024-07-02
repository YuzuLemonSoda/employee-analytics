package com.example.employee_analytics.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeesResponseDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private Long departmentId;

}
