package com.example.employee_analytics.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class DepartmentAvgSalaryResponseDTO {

    private String jobId;

    private BigDecimal averageSalary;

}
