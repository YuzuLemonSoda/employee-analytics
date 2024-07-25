package com.example.employee_analytics.dtos.response;

import java.math.BigDecimal;

public interface MedianSalaryByDeptResponseDTO {

    String getJobId();

    BigDecimal getMedianSalary();

}
