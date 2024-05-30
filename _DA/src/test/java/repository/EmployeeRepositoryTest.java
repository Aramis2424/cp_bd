package repository;

import entity.EmployeeEntity;
import irepository.IEmployeeRepository;
import model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRepositoryTest {
    private IEmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeRepository = new EmployeeRepository();
    }

    @Test
    void insertEmployee() {
        Employee employee = Employee.createEmployeeModel("Ruslan", "Ruslanov", 1, 1);
        int id = employeeRepository.insertEmployee(employee);

        Employee newEmployee = employeeRepository.getEmployeeById(id);

        assertEquals("Ruslan", newEmployee.getFirstName());
    }

    @Test
    void updateEmployee() {
        Employee employee = Employee.createEmployeeModel("Ruslan", "Ruslanov", 1, 1);
        int id = employeeRepository.insertEmployee(employee);

        Employee newEmployee = employeeRepository.getEmployeeById(id);
        newEmployee.setFirstName("Ivan");
        employeeRepository.updateEmployee(newEmployee);

        newEmployee = employeeRepository.getEmployeeById(id);
        assertEquals("Ivan", newEmployee.getFirstName());
    }

    @Test
    void deleteEmployee() {
        Employee employee = Employee.createEmployeeModel("Ruslan", "Ruslanov", 1, 1);
        int id = employeeRepository.insertEmployee(employee);

        employeeRepository.deleteEmployee(id);

        Employee newEmployee = employeeRepository.getEmployeeById(id);
        assertNull(newEmployee);
    }

    @Test
    void getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>(3);
        employeeList.add(Employee.createEmployeeModel("Ruslan", "Ruslanov", 1, 1));
        employeeList.add(Employee.createEmployeeModel("Ivan", "Ivanov", 2, 1));
        employeeList.add(Employee.createEmployeeModel("Petr", "Petrov", 3, 1));

        for (Employee employee : employeeList)
            employeeRepository.insertEmployee(employee);

        List<Employee> employees = employeeRepository.getAllEmployees();

        assertNotNull(employees);
        assertTrue(employees.size() >= 3);
    }

    @Test
    void getEmployeeById() {
        Employee employee = Employee.createEmployeeModel("Ruslan", "Ruslanov", 1, 1);
        int id = employeeRepository.insertEmployee(employee);

        Employee newEmployee = employeeRepository.getEmployeeById(id);

        assertEquals("Ruslan", newEmployee.getFirstName());
    }
}