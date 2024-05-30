package repository;

import irepository.IAutoOwnerRepository;

import model.AutoOwner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AutoOwnerRepositoryTest {
    private IAutoOwnerRepository autoOwnerRepository;

    @BeforeEach
    void setUp() {
        autoOwnerRepository = new AutoOwnerRepository();
    }

    @Test
    void insertAutoOwner() {
        AutoOwner autoOwner = AutoOwner.createAutoOwnerModel("Aaa", "bbb",
                "ab@com", "qaz", 5000, 1, LocalDateTime.now());
        int id = autoOwnerRepository.insertAutoOwner(autoOwner);

        AutoOwner newAutoOwner = autoOwnerRepository.getAutoOwnerByID(id);
        assertEquals("Aaa", newAutoOwner.getFirstName());
    }

    @Test
    void updateAutoOwner() {
        AutoOwner autoOwner = AutoOwner.createAutoOwnerModel("Aaa", "bbb",
                "ab@com", "qaz", 5000, 1, LocalDateTime.now());
        int id = autoOwnerRepository.insertAutoOwner(autoOwner);

        AutoOwner newAutoOwner = autoOwnerRepository.getAutoOwnerByID(id);
        newAutoOwner.setAccount(4999);
        autoOwnerRepository.updateAutoOwner(newAutoOwner);

        newAutoOwner = autoOwnerRepository.getAutoOwnerByID(id);
        assertEquals(4999, newAutoOwner.getAccount());
    }

    @Test
    void deleteAutoOwner() {
        AutoOwner autoOwner = AutoOwner.createAutoOwnerModel("Deleter", "bbb",
                "ab@com", "qaz", 5000, 1, LocalDateTime.now());
        int id = autoOwnerRepository.insertAutoOwner(autoOwner);

        autoOwnerRepository.deleteAutoOwner(id);

        AutoOwner deletedAutoOwner = autoOwnerRepository.getAutoOwnerByID(id);
        assertNull(deletedAutoOwner);
    }

    @Test
    void getAutoOwnerByID() {
        AutoOwner autoOwner = AutoOwner.createAutoOwnerModel("Aaa", "bbb",
                "ab@com", "qaz", 5000, 1, LocalDateTime.now());
        int id = autoOwnerRepository.insertAutoOwner(autoOwner);

        AutoOwner newAutoOwner = autoOwnerRepository.getAutoOwnerByID(id);
        assertEquals("Aaa", newAutoOwner.getFirstName());
    }

    @Test
    void getAutoOwnerBySignInfo() {
        AutoOwner autoOwner = AutoOwner.createAutoOwnerModel("Aaa", "bbb",
                "abQ@com", "qaz", 5000, 1, LocalDateTime.now());
        int id = autoOwnerRepository.insertAutoOwner(autoOwner);

        AutoOwner newAutoOwner = autoOwnerRepository.getAutoOwnerBySignInfo("abQ@com", "qaz");
        assertEquals("Aaa", newAutoOwner.getFirstName());
    }

    @Test
    void getAllAutoOwners() {
        List<AutoOwner> autoOwnerList = new ArrayList<>();
        autoOwnerList.add(AutoOwner.createAutoOwnerModel("Aaa", "bbb",
                "ab@com", "qaz", 5000, 1, LocalDateTime.now()));
        autoOwnerList.add(AutoOwner.createAutoOwnerModel("Zzz", "Zzz",
                "zz@com", "321", 5000, 1, LocalDateTime.now()));
        autoOwnerList.add(AutoOwner.createAutoOwnerModel("Yyy", "Yyy",
                "yy@com", "123", 5000, 1, LocalDateTime.now()));

        for (AutoOwner autoOwner : autoOwnerList) {
            autoOwnerRepository.insertAutoOwner(autoOwner);
        }

        List<AutoOwner> autoOwners = autoOwnerRepository.getAllAutoOwners();

        assertEquals(autoOwnerList.size(), autoOwners.size());
        assertEquals(autoOwnerList.get(0).getFirstName(), autoOwners.get(0).getFirstName());
    }
}