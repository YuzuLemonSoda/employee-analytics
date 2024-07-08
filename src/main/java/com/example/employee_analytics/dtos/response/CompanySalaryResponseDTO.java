package com.example.employee_analytics.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CompanySalaryResponseDTO {

    private BigDecimal salary;

}
