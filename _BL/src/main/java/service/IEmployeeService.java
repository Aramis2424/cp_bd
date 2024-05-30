package service;

import java.util.List;

import model.Employee;

public interface IEmployeeService {
    public int createEmployee(Employee employee);

    public void updateEmployee(Employee employee);

    public void removeEmployee(int id);

    public List<Employee> getAllEmployees();

    public Employee getEmployeeByID(int id);
}
