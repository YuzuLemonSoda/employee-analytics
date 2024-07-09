package groovy

import com.example.employee_analytics.models.entities.Employees
import com.example.employee_analytics.repository.EmployeeRepository
import com.example.employee_analytics.service.impl.EmployeesServiceImpl
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class EmployeeServiceImplTest extends Specification {

    EmployeesServiceImpl employeesService = Mock()
    EmployeeRepository employeeRepository = Mock()

    def setup() {
        employeesService = new EmployeesServiceImpl(employeeRepository)
    }

    def "Test getEmployeesWithFirstName method with existing employees"() {
        given:
        def firstName = "John"
        def employees = [
                new Employees(id: 1, firstName: "John", lastName: "Doe", departmentId: 101),
                new Employees(id: 2, firstName: "John", lastName: "Smith", departmentId: 102)
        ]
        employeeRepository.findAllByFirstName(firstName) >> Optional.of(employees)

        when:
        def result = employeesService.getEmployeesWithFirstName(firstName)

        then:
        result != null
        result.size() == 2
        result[0].firstName == "John"
        result[1].firstName == "John"
    }

    def "Test getEmployeesWithFirstName method with non-existing employees"() {
        given:
        def firstName = "Alice"
        employeeRepository.findAllByFirstName(firstName) >> Optional.empty()

        when:
        employeesService.getEmployeesWithFirstName(firstName)

        then:
        thrown(RuntimeException)
    }

    def "getCompanyAverageSalary returns correct average salary"() {

        given:
        employeeRepository.findAverageSalary() >> averageSalary

        when:
        def result = employeesService.getCompanyAverageSalary()

        then:
        result.getSalary() == averageSalary

        where:
        averageSalary << new BigDecimal("50000.00")
    }

}
