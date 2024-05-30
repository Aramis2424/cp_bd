package tui.view;

import model.Employee;

public class EmployeeView {
    public void printEmployee(int index, Employee employee, String job, String parking) {
        System.out.printf("%d) %s, %s - %s; Парковка: %s\n",
                index, employee.getFirstName(), employee.getLastName(), job, parking);
    }
}
