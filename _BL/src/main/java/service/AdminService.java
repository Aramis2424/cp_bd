package service;

import irepository.IAdminRepository;
import model.Admin;

public class AdminService implements IAdminService {
    private final IAdminRepository adminRep;

    public AdminService(IAdminRepository adminRepository) {
        adminRep = adminRepository;
    }

    @Override
    public Boolean signIn(String login, String password) {
        if (this.getAdminBySignInfo(login, password) != null)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    @Override
    public Admin getAdminBySignInfo(String login, String password) {
        return adminRep.getAdminBySignInfo(login, password);
    }
}
