package model;

import exception.InvalidArgumentException;

public class Admin {
    private int id;
    private String login;
    private String password;

    public Admin() {
    }

    public Admin(int id, String login, String password) {
        this.login = login;
        this.id = id;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Admin createAdminModel(int idAdmin, String loginAdmin, String passwordAdmin) {
        validateArguments(idAdmin, loginAdmin, passwordAdmin);
        return new Admin(-1, loginAdmin, passwordAdmin);
    }

    public static void validateArguments(int idAdmin, String loginAdmin, String passwordAdmin) {
        if (loginAdmin.isBlank())
            throw new InvalidArgumentException("Blank login Admin");
        if (passwordAdmin.isBlank())
            throw new InvalidArgumentException("Blank password Admin");

    }
}
