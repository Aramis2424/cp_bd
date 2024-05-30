package tui.controller;

import repository.AdminRepository;
import tui.view.AdminView;
import model.Admin;
import service.AdminService;

import java.util.Map;

public class AdminController {
    private Admin admin;
    private EmployeeController employeeController = new EmployeeController();
    private JobController jobController = new JobController();
    private AdminView adminView = new AdminView();
    private AdminRepository adminRepository = new AdminRepository();
    private AdminService adminService = new AdminService(adminRepository);
    private ParkingController parkingController = new ParkingController();

    public void execute() {
        printAdminMenu();
    }

    public void printAdminMenu() {
        while (true) {
            adminView.printAdminMenu();
            int choice = adminView.getNumber("Ваш выбор: ");

            switch (choice) {
                case 0 -> {return;}
                case 1 -> {
                    addEmployee();}
                case 2 -> {
                    removeEmployee();}
            }
        }
    }

    private void removeEmployee() {
        Map<Integer, Integer> ids = employeeController.makeChoiceEmployees();
        int choiceEmployee = adminView.getNumber("Выберите индекс сотрудника: ");
        int id = ids.get(choiceEmployee);
        employeeController.fireEmployee(id);
    }

    private void addEmployee() {
        String name = adminView.getString("Введите имя сотрудника: ");
        String surname = adminView.getString("Введите фамилию сотрудника: ");
        Map<Integer, Integer> idsP = parkingController.makeChoiceParkings();
        int choiceParking = adminView.getNumber("Выберите индекс парковки: ");
        int parkingId = idsP.get(choiceParking);
        Map<Integer, Integer> idsJ = jobController.makeChoiceJobs();
        int choiceJob = adminView.getNumber("Выберите должность: ");
        int jobId = idsJ.get(choiceJob);
        employeeController.addEmployee(name, surname, jobId, parkingId);
    }

    public boolean signIn() {
        String login = adminView.getString("Введите ваш логин: ");
        String password = adminView.getString("Введите ваш пароль: ");
        boolean res = adminService.signIn(login, password);
        if (res)
            adminView.printSuccessSignIn();
        else
            adminView.printNonSuccessSignIn();
        admin = adminService.getAdminBySignInfo(login, password);
        return res;
    }

    public Admin getAdmin() {
        return admin;
    }
}
