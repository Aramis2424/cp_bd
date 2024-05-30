package irepository;

import model.Employee;

import java.util.List;

public interface IEmployeeRepository {
    public int insertEmployee(Employee employees);

    public void updateEmployee(Employee employee);

    public void deleteEmployee(int employeeId);

    public List<Employee> getAllEmployees();

    public Employee getEmployeeById(int employeeId);
}
