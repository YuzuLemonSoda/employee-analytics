package com.example.employee_analytics.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface FirstEmployeesToJoinResponseDTO {

    Long getEmployeeId();

    String getFirstName();

    String getLastName();

    String getEmail();

    String getPhoneNumber();

    LocalDateTime getHireDate();

    String getJobId();

    BigDecimal getSalary();

    Long getDepartmentId();

}
