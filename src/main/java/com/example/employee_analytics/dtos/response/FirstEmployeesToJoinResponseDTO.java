package com.example.employee_analytics.dtos.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public interface FirstEmployeesToJoinResponseDTO {

    Long getEmployeeId();

    String getFirstName();

    String getLastName();

    String getEmail();

    String getPhoneNumber();

    OffsetDateTime getHireDate();

    String getJobId();

    BigDecimal getSalary();

    Long getDepartmentId();

}
