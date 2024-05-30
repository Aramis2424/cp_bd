package irepository;

import model.Admin;

public interface IAdminRepository {
    public Admin getAdminBySignInfo(String login, String password);
}
