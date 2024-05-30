package service;

import exception.NotEnoughMoneyException;
import exception.UserAlreadyExistsException;
import irepository.IAutoOwnerRepository;
import model.AutoOwner;
import model.Car;
import model.Job;
import model.Subscription;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AutoOwnerServiceTest {
    @Mock
    private IAutoOwnerRepository mockAutoOwnerRep;
    @InjectMocks
    private AutoOwnerService service;

    @Test
    public void signIn_ExistingUser() {
        AutoOwner autoOwner = new AutoOwner(1, "Aaa", "bbb",
                "ab", "123", 10, 2, LocalDateTime.now());
        Mockito.when(mockAutoOwnerRep.getAutoOwnerBySignInfo("Aaa", "123")).thenReturn(autoOwner);

        Boolean actualIsExisting = service.signIn("Aaa", "123");

        assertEquals(Boolean.TRUE, actualIsExisting);
    }

    @Test
    public void signIn_NonExistingUser() {
        Mockito.when(mockAutoOwnerRep.getAutoOwnerBySignInfo("Aaa", "123")).thenReturn(null);

        Boolean actualIsExisting = service.signIn("Aaa", "123");

        assertEquals(Boolean.FALSE, actualIsExisting);
    }

    @Test
    public void signUp_ExistingUser() {
        AutoOwner autoOwner = new AutoOwner(-1, "Aaa", "bbb",
                "ab", "123", 10, 2, LocalDateTime.now());
        Mockito.when(mockAutoOwnerRep.getAutoOwnerBySignInfo("ab", "123")).thenReturn(autoOwner);

        assertThrows(UserAlreadyExistsException.class, () -> { service.signUp(autoOwner); });
    }

    @Test
    public void signUp_NonExistingUser() {
        AutoOwner autoOwner = new AutoOwner(-1, "Aaa", "bbb",
                "ab", "123", 10, 2, LocalDateTime.now());
        Mockito.when(mockAutoOwnerRep.getAutoOwnerBySignInfo("ab", "123")).thenReturn(null);
        Mockito.when(mockAutoOwnerRep.insertAutoOwner(autoOwner)).thenReturn(1);

        service.signUp(autoOwner);

        assertEquals(1, autoOwner.getId());
    }

    @Test
    public void updateAutoOwner() {
        AutoOwner autoOwner = new AutoOwner(-1, "Aaa", "bbb",
                "ab", "123", 10, 2, LocalDateTime.now());
        Mockito.doNothing().when(mockAutoOwnerRep).updateAutoOwner(autoOwner);

        service.updateAutoOwner(autoOwner);

        Mockito.verify(mockAutoOwnerRep, Mockito.times(1)).updateAutoOwner(autoOwner);
    }

    @Test
    public void removeAutoOwner() {
        int removingAutoOwnerId = 1;
        Mockito.doNothing().when(mockAutoOwnerRep).deleteAutoOwner(removingAutoOwnerId);

        service.removeAutoOwner(removingAutoOwnerId);

        Mockito.verify(mockAutoOwnerRep).deleteAutoOwner(removingAutoOwnerId);
    }

    @Test
    public void debitAccount_NonEnoughMoney() {
        int account = 100;
        AutoOwner autoOwner = new AutoOwner(1, "Aaa", "bbb",
                "ab", "123", account, 2, LocalDateTime.now());
        Mockito.when(mockAutoOwnerRep.getAutoOwnerByID(1)).thenReturn(autoOwner);
        int money = 200;

        assertThrows(NotEnoughMoneyException.class, () -> { service.debitAccount(1, money);});
    }
    @Test
    public void debitAccount_EnoughMoney() {
        int account = 100;
        AutoOwner autoOwner = new AutoOwner(1, "Aaa", "bbb",
                "ab", "123", account, 2, LocalDateTime.now());
        Mockito.when(mockAutoOwnerRep.getAutoOwnerByID(1)).thenReturn(autoOwner);
        int money = 40;

        service.debitAccount(1, money);

        assertEquals(60, autoOwner.getAccount());
    }

    @Test
    public void topUpAccount() {
        int account = 100;
        AutoOwner autoOwner = new AutoOwner(1, "Aaa", "bbb",
                "ab", "123", account, 2, LocalDateTime.now());
        Mockito.when(mockAutoOwnerRep.getAutoOwnerByID(1)).thenReturn(autoOwner);
        int money = 50;

        service.topUpAccount(1, money);

        assertEquals(150, autoOwner.getAccount());
    }

    @Test
    public void getAutoOwnerByID() {
        AutoOwner expected = new AutoOwner(1, "Aaa", "bbb",
                "ab", "123", 100, 2, LocalDateTime.now());
        Mockito.when(mockAutoOwnerRep.getAutoOwnerByID(1)).thenReturn(expected);

        AutoOwner actual = service.getAutoOwnerByID(1);

        assertEquals(expected, actual);
    }

    @Test
    public void getAutoOwnerBySignInfo() {
        AutoOwner expected = new AutoOwner(1, "Aaa", "bbb",
                "ab", "123", 100, 2, LocalDateTime.now());
        Mockito.when(mockAutoOwnerRep.getAutoOwnerBySignInfo("ab", "123")).thenReturn(expected);

        AutoOwner actual = service.getAutoOwnerBySignInfo("ab", "123");

        assertEquals(expected, actual);
    }

    @Test
    public void getAllAutoOwners() {
        List<AutoOwner> autoOwnerList = new ArrayList<>();
        AutoOwner autoOwner1 = new AutoOwner(1, "Aaa", "bbb",
                "ab", "123", 100, 2, LocalDateTime.now());
        AutoOwner autoOwner2 = new AutoOwner(2, "Ccc", "vvv",
                "cv", "321", 125, 2, LocalDateTime.now());
        autoOwnerList.add(autoOwner1);
        autoOwnerList.add(autoOwner2);
        Mockito.when(service.getAllAutoOwners()).thenReturn(autoOwnerList);

        List<AutoOwner> actualautoOwnerList = service.getAllAutoOwners();

        assertArrayEquals(autoOwnerList.toArray(), actualautoOwnerList.toArray());
    }

    @Test
    public void updateSubscription() {
        int subscriptionId = 5;
        AutoOwner autoOwner = new AutoOwner(1, "Aaa", "bbb",
                "ab", "123", 100, subscriptionId, LocalDateTime.now());
        Mockito.when(mockAutoOwnerRep.getAutoOwnerByID(1)).thenReturn(autoOwner);
        Subscription newSubscription = Subscription.createSubscriptionModel("test",
                10, 0, Period.of(1,0,0), 0);

        service.updateSubscription(1, newSubscription);

        assertEquals(newSubscription.getId(), autoOwner.getSubscriptionFID());
        Mockito.verify(mockAutoOwnerRep, Mockito.times(1)).updateAutoOwner(Mockito.any());
    }
}