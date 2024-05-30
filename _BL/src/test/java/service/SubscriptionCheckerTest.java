package service;

import model.AutoOwner;
import model.Subscription;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class SubscriptionCheckerTest {
    @Mock
    private IAutoOwnerService mockService;
    @Mock
    private ISubscriptionService mockSubscriptionService;

    @InjectMocks
    private SubscriptionChecker checker;

    @Test
    void getDelay() {
        int expectedDelay = 24 - LocalDateTime.now().getHour();
        int actualDelay = SubscriptionChecker.getDelay();

        assertEquals(expectedDelay, actualDelay);
    }

    @Test
    void initImmediatelyChecking() {
        List<AutoOwner> autoOwnerList = new ArrayList<>();
        autoOwnerList.add(new AutoOwner(1, "aaa", "zzz",
                "a@exaple", "1234", 0, 1, LocalDateTime.now().minusHours(1)));
        autoOwnerList.add(new AutoOwner(2, "bbb", "yyy",
                "b@exaple", "1234", 0, 1, LocalDateTime.now().plusHours(1)));
        autoOwnerList.add(new AutoOwner(3, "ccc", "qqq",
                "c@exaple", "1234", 0, 1, LocalDateTime.now().minusHours(5)));

        Subscription subscription = new Subscription();
        Mockito.when(mockService.getAllAutoOwners()).thenReturn(autoOwnerList);
        Mockito.when(mockSubscriptionService.getSubscriptionById(1)).thenReturn(subscription);

        checker.initImmediatelyChecking();

        Mockito.verify(mockService, Mockito.times(2))
                .updateSubscription(Mockito.anyInt(), Mockito.any());
        Mockito.verify(mockService).updateSubscription(1, Mockito.any());
        Mockito.verify(mockService).updateSubscription(3, Mockito.any());
        Mockito.verify(mockService, Mockito.never()).updateSubscription(2, Mockito.any());
    }
}
