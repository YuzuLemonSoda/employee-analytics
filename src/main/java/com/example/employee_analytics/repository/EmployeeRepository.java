package com.example.employee_analytics.repository;

import com.example.employee_analytics.dtos.response.DepartmentAnnualPayrollResponseDTO;
import com.example.employee_analytics.dtos.response.DepartmentAvgSalaryResponseDTO;
import com.example.employee_analytics.dtos.response.EmployeesByDeptResponseDTO;
import com.example.employee_analytics.dtos.response.MedianSalaryByDeptResponseDTO;
import com.example.employee_analytics.models.entities.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long> {

    Optional<List<Employees>> findAllByFirstName(String firstName);

    @Query(value = "SELECT ROUND(AVG(salary), 2) AS average_salary FROM employees", nativeQuery = true)
    BigDecimal findAverageSalary();

    @Query(nativeQuery = true)
    List<DepartmentAvgSalaryResponseDTO> findDepartmentAverageSalaries();

    @Query(value = "SELECT job_id, COUNT(*) AS total FROM employees  GROUP BY job_id ORDER BY job_id", nativeQuery = true)
    List<EmployeesByDeptResponseDTO> findEmployeesByDepartment();

    @Query(value = "SELECT job_id, SUM(salary) as annual_payroll FROM employees GROUP BY job_id ORDER BY job_id", nativeQuery = true)
    List<DepartmentAnnualPayrollResponseDTO> findDepartmentAnnualSalaries();

    @Query(value = "WITH RankedSalaries AS ( SELECT job_id, salary, ROW_NUMBER() OVER (PARTITION BY job_id ORDER BY salary)" +
            "AS row_num, COUNT(*) OVER (PARTITION BY job_id) AS total_count FROM employees )" +
            "SELECT job_id, AVG(salary) AS median_salary FROM RankedSalaries WHERE row_num IN " +
            "(FLOOR((total_count + 1) / 2.0), CEIL((total_count + 1) / 2.0)) GROUP BY job_id ORDER BY job_id ", nativeQuery = true)
    List<MedianSalaryByDeptResponseDTO> findMedianSalaryByDepartment();
}
