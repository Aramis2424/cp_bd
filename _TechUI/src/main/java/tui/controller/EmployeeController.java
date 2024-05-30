package tui.controller;

import model.Employee;
import repository.EmployeeRepository;
import service.EmployeeService;
import tui.view.EmployeeView;
import tui.view.ParkingView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeController {
    private EmployeeView employeeView = new EmployeeView();
    private ParkingController parkingController = new ParkingController();
    private EmployeeRepository employeeRepository = new EmployeeRepository();
    private EmployeeService employeeService = new EmployeeService(employeeRepository);
    private JobController jobController = new JobController();

    public void addEmployee(String firstName, String lastName, int jobId, int parkingId) {
        Employee employee = Employee.createEmployeeModel(firstName, lastName, jobId, parkingId);

        employeeService.createEmployee(employee);
    }

    public Map<Integer, Integer> makeChoiceEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        Map<Integer, Integer> indexToId = new HashMap<>();
        int index = 1;
        for (Employee employee : employees) {
            String job = jobController.getJobTitle(employee.getJobFID());
            String parking = parkingController.getParkingAddress(employee.getParkingFID());
            employeeView.printEmployee(index, employee, job, parking);
            indexToId.put(index, employee.getId());
            index++;
        }
        return indexToId;
    }

    public void fireEmployee(int id) {
        employeeService.removeEmployee(id);
    }
}
