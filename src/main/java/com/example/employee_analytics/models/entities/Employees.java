package com.example.employee_analytics.models.entities;

import com.example.employee_analytics.dtos.response.DepartmentAvgSalaryResponseDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "employees", schema = "company_roster")
@NamedNativeQuery(name = "Employees.findDepartmentAverageSalaries",
                  query = "SELECT e.job_id AS jobId, ROUND(AVG(e.salary), 2) AS averageSalary FROM employees e GROUP BY e.job_id",
                  resultSetMapping = "Mapping.DepartmentAvgSalaryResponseDTO")
@SqlResultSetMapping(name = "Mapping.DepartmentAvgSalaryResponseDTO",
                     classes = @ConstructorResult(targetClass = DepartmentAvgSalaryResponseDTO.class,
                                                   columns = { @ColumnResult(name = "jobId"),
                                                                @ColumnResult(name = "averageSalary")
                                                   }))
public class Employees {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "hire_date")
    private LocalDateTime hireDate;

    @Column(name = "job_id")
    private String jobId;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "department_id")
    private Long departmentId;

}
