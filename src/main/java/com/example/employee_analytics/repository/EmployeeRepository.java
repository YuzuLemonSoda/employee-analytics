package com.example.employee_analytics.repository;

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
}
