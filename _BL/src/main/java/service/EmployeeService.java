package service;

import irepository.IEmployeeRepository;
import model.Employee;

import java.util.List;

public class EmployeeService implements IEmployeeService {
    private IEmployeeRepository employeeRep;

    public EmployeeService(IEmployeeRepository employeeRepository){employeeRep = employeeRepository;}
    @Override
    public int createEmployee(Employee employee) {
        int id = employeeRep.insertEmployee(employee);
        employee.setId(id);
        return id;
    }

    @Override
    public void updateEmployee(Employee employee) { employeeRep.updateEmployee(employee);}

    @Override
    public void removeEmployee(int id) {employeeRep.deleteEmployee(id);}

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRep.getAllEmployees();
    }

    @Override
    public Employee getEmployeeByID(int id) {
        return employeeRep.getEmployeeById(id);
    }
}
