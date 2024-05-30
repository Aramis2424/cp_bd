package service;

import model.Admin;

public interface IAdminService {
    public Boolean signIn(String login, String password);
    public Admin getAdminBySignInfo(String login, String password);
}
