package irepository;

import model.AutoOwner;

import java.util.List;

public interface IAutoOwnerRepository {
    public int insertAutoOwner(AutoOwner autoOwner);

    public void updateAutoOwner(AutoOwner autoOwner);

    public void deleteAutoOwner(int autoOwnerId);

    public AutoOwner getAutoOwnerByID(int autoOwnerId);

    public AutoOwner getAutoOwnerBySignInfo(String login, String password);

    public List<AutoOwner> getAllAutoOwners();
}
