package tui.controller;

import tui.view.MenuView;

public class MenuController {
    private MenuView menuView = new MenuView();
    private AutoOwnerController autoOwnerController = new AutoOwnerController();
    private ParkingController parkingController = new ParkingController();
    private BookingController bookingController = new BookingController();
    private TicketController ticketController = new TicketController();
    private AdminController adminController = new AdminController();

    public void execute() {
        startMenu();
        if (adminController.getAdmin() != null)
            adminController.execute();
        else if (autoOwnerController.getAutoOwner() != null)
            mainMenu();
    }

    public void startMenu() {
        boolean repeat = true;
        while (repeat) {
            menuView.printStartMenu();
            int choice = menuView.getNumber("Ваш выбор: ");

            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    if (autoOwnerController.signIn())
                        repeat = false;
                }
                case 2 -> {
                    autoOwnerController.signUp();
                    repeat = false;
                }
                case 3 -> {
                    if (adminController.signIn())
                        repeat = false;
                }
            }
        }
    }

    public void mainMenu() {
        while (true) {
            menuView.printBaseMenu();

            int choice = menuView.getNumber("Ваш выбор: ");

            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> parkingController.execute();
                case 2 -> bookingController.execute(autoOwnerController.getAutoOwner().getId());
                case 3 -> ticketController.execute(autoOwnerController.getAutoOwner().getId());
                case 4 -> autoOwnerController.execute();
            }
        }
    }
}
