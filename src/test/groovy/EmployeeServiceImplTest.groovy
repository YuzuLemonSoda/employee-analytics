package groovy

import com.example.employee_analytics.dtos.response.DepartmentAnnualPayrollResponseDTO
import com.example.employee_analytics.dtos.response.DepartmentAvgSalaryResponseDTO
import com.example.employee_analytics.dtos.response.EmployeesByDeptResponseDTO
import com.example.employee_analytics.dtos.response.FirstEmployeesToJoinResponseDTO
import com.example.employee_analytics.dtos.response.MedianSalaryByDeptResponseDTO
import com.example.employee_analytics.models.entities.Employees
import com.example.employee_analytics.repository.EmployeeRepository
import com.example.employee_analytics.service.impl.EmployeesServiceImpl
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

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

    def "getDepartmentAverageSalary returns successful average salary for each department" () {
        given:
        List<DepartmentAvgSalaryResponseDTO> expectedSalaries = [
                new DepartmentAvgSalaryResponseDTO("JOB001", new BigDecimal("5000.00")),
                new DepartmentAvgSalaryResponseDTO("JOB002", new BigDecimal("6000.00"))
        ]

        def repository = Mock(EmployeeRepository) {
            findDepartmentAverageSalaries() >> expectedSalaries
        }

        EmployeesServiceImpl employeesService = new EmployeesServiceImpl(repository)

        when:
        List<DepartmentAvgSalaryResponseDTO> actualSalaries = employeesService.getDepartmentAverageSalary()

        then:
        actualSalaries.size() == 2
        actualSalaries[0].jobId == "JOB001"
        actualSalaries[0].averageSalary == new BigDecimal("5000.00")
        actualSalaries[1].jobId == "JOB002"
        actualSalaries[1].averageSalary == new BigDecimal("6000.00")

        }

        def "getEmployeesByDepartment returns successful headcount of each department " () {
            given:
            def employeesByDeptResponseDTO1 = Mock(EmployeesByDeptResponseDTO) {
                getJobId() >> "DEV"
                getTotal() >> 10L
            }
            def employeesByDeptResponseDTO2 = Mock(EmployeesByDeptResponseDTO) {
                getJobId() >> "HR"
                getTotal() >> 5L
            }
            def employeesByDeptList = [employeesByDeptResponseDTO1, employeesByDeptResponseDTO2]
            employeeRepository.findEmployeesByDepartment() >> employeesByDeptList

            when:
            def result = employeesService.getEmployeesByDepartment()

            then:
            result.size() == 2
            result[0].jobId == "DEV"
            result[0].total == 10L
            result[1].jobId == "HR"
            result[1].total == 5L
        }

        def "getDepartmentAnnualPayroll returns successful payroll for each department " () {
            given:
            def departmentAnnualPayrollResponseDTO1 = Mock(DepartmentAnnualPayrollResponseDTO) {
                getJobId() >> "Dev"
                getAnnualPayroll() >> 10L

            }
            def departmentAnnualPayrollResponseDTO2 = Mock(DepartmentAnnualPayrollResponseDTO) {
                getJobId() >> "HR"
                getAnnualPayroll() >> 20L
            }
            def departmentAnnualPayrollList = [departmentAnnualPayrollResponseDTO1, departmentAnnualPayrollResponseDTO2]
            employeeRepository.findDepartmentAnnualSalaries() >> departmentAnnualPayrollList

            when:
            def result = employeesService.getDepartmentAnnualPayroll()
            then:
            result.size() == 2
            result[0].jobId == "Dev"
            result[0].annualPayroll == 10L
            result[1].jobId == "HR"
            result[1].annualPayroll == 20L
        }

        def "getMedianSalaryByDepartment returns successfully and gets the median salary of each department " () {
            given:
            def medianSalaryByDeptResponseDTO1 = Mock(MedianSalaryByDeptResponseDTO) {
                getJobId() >> "Dev"
                getMedianSalary() >> 10L
            }
            def medianSalaryByDeptResponseDTO2 = Mock(MedianSalaryByDeptResponseDTO) {
                getJobId() >> "HR"
                getMedianSalary() >> 20L
            }

            def medianSalaryList = [medianSalaryByDeptResponseDTO1, medianSalaryByDeptResponseDTO2]
            employeeRepository.findMedianSalaryByDepartment() >> medianSalaryList

            when:
            def result = employeesService.getMedianSalaryByDepartment()
            then:
            result.size() == 2
            result[0].jobId == "Dev"
            result[0].medianSalary == 10L
            result[1].jobId == "HR"
            result[1].medianSalary == 20L
        }

        def "getFirstEmployeesToJoin returns successfully and gets the first employees to join the company " () {

            given:
            def firstEmployeesToJoinResponseDTO1 = Mock(FirstEmployeesToJoinResponseDTO) {
                getEmployeeId() >> 1234
                getFirstName() >> "John"
                getLastName() >> "Smith"
                getEmail() >> "jsmith01@email.com"
                getPhoneNumber() >> "123456790"
                getHireDate() >> OffsetDateTime.parse("2014-07-01T05:00:00.000+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                getJobId() >> "Dev"
                getSalary() >> 75000.00
                getDepartmentId() >> 10
            }
            def firstEmployeesToJoinResponseDTO2 = Mock(FirstEmployeesToJoinResponseDTO) {
                getEmployeeId() >> 1235
                getFirstName() >> "Jane"
                getLastName() >> "Doe"
                getEmail() >> "jdoe03@email.com"
                getPhoneNumber() >> "1234447895"
                getHireDate() >> OffsetDateTime.parse("2014-07-01T05:00:00.000+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                getJobId() >> "HR"
                getSalary() >> 85000.00
                getDepartmentId() >> 3
            }
            def firstEmployeesToJoinResponseDTOList = [firstEmployeesToJoinResponseDTO1, firstEmployeesToJoinResponseDTO2]
            employeeRepository.findFirstEmployeesToJoin() >> firstEmployeesToJoinResponseDTOList

            when:
            def result = employeesService.getFirstEmployeesToJoin()

            then:
            result.size() == 2
            result[0].getEmployeeId() == 1234
            result[0].getFirstName() == "John"
            result[0].getLastName() == "Smith"
            result[0].getEmail() == "jsmith01@email.com"
            result[0].getPhoneNumber() == "123456790"
            result[0].getHireDate().isEqual(OffsetDateTime.parse("2014-07-01T05:00:00.000+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME))
            result[0].getJobId() == "Dev"
            result[0].getSalary() == 75000.00
            result[0].getDepartmentId() == 10

            result[1].getEmployeeId() == 1235
            result[1].getFirstName() == "Jane"
            result[1].getLastName() == "Doe"
            result[1].getEmail() == "jdoe03@email.com"
            result[1].getPhoneNumber() == "1234447895"
            result[1].getHireDate().isEqual(OffsetDateTime.parse("2014-07-01T05:00:00.000+00:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME))
            result[1].getJobId() == "HR"
            result[1].getSalary() == 85000.00
            result[1].getDepartmentId() == 3
        }
}
