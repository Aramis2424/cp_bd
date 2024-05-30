package service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import model.Employee;
import irepository.IEmployeeRepository;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private IEmployeeRepository mockRep;

    @InjectMocks
    private EmployeeService service;

    @Test
    public void createEmployee() {
        Employee newEmployee = new Employee(33, "Jack", "Black", 1, 1);

        Mockito.when(mockRep.insertEmployee(newEmployee)).thenReturn(33);

        service.createEmployee(newEmployee);

        Mockito.verify(mockRep).insertEmployee(newEmployee);
        assertEquals(33, newEmployee.getId());
    }

    @Test
    public void updateEmployee() {
        Employee updatingEmployee = new Employee(1, "Larry", "Lazy", 1, 2);
        Mockito.doNothing().when(mockRep).updateEmployee(updatingEmployee);

        service.updateEmployee(updatingEmployee);

        Mockito.verify(mockRep).updateEmployee(updatingEmployee);
    }

    @Test
    public void removeEmployee() {
        int removingEmployeeId = 33;
        Mockito.doNothing().when(mockRep).deleteEmployee(removingEmployeeId);

        service.removeEmployee(removingEmployeeId);

        Mockito.verify(mockRep).deleteEmployee(removingEmployeeId);
    }

    @Test
    public void getEmployeeById() {
        Employee expected = new Employee(1, "Godard", "Stark", 1, 1);
        Mockito.when(mockRep.getEmployeeById(1)).thenReturn(expected);

        Employee actual = service.getEmployeeByID(1);
        assertEquals(expected, actual);
    }

    @Test
    public void getAllEmployee() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Godard", "Stark", 1, 1));
        employees.add(new Employee(2, "Tony", "Stark", 1, 1));
        employees.add(new Employee(3, "Margaret", "Stark", 1, 1));

        Mockito.when(mockRep.getAllEmployees()).thenReturn(employees);

        List<Employee> retrievedEmployees = service.getAllEmployees();

        Mockito.verify(mockRep).getAllEmployees();
        assertEquals(employees, retrievedEmployees);
    }
}
