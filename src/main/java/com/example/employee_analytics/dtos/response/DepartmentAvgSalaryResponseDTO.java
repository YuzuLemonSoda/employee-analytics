package com.example.employee_analytics.dtos.response;

import java.math.BigDecimal;


public interface DepartmentAvgSalaryResponseDTO {

    String getJobId();

    BigDecimal getAverageSalary();

}
