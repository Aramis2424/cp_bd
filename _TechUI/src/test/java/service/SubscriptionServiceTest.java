package service;

import irepository.ISubscriptionRepository;
import model.Subscription;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SubscriptionServiceTest {
    @Mock
    private ISubscriptionRepository mockRep;

    @InjectMocks
    private SubscriptionService service;

    @Test
    public void createSubscription(){
        Subscription newSubscription = new Subscription(1, "Hello", 300, 2.1d, Period.ofDays(2), 24);
        Mockito.when(mockRep.insertSubscription(newSubscription)).thenReturn(1);

        service.createSubscription(newSubscription);
        Mockito.verify(mockRep).insertSubscription(newSubscription);
        assertEquals(1, newSubscription.getId());
    }

    @Test
    public void  updatingSubscription(){
        Subscription updatingSubscription = new Subscription(1, "Hello", 300, 2.1d, Period.ofDays(2), 24);
        Mockito.doNothing().when(mockRep).updateSubscription(updatingSubscription);

        service.updateSubscription(updatingSubscription);
        Mockito.verify(mockRep).updateSubscription(updatingSubscription);
    }

    @Test
    public void removeSubscription(){
        int removeSubscriptionId = 1;
        Mockito.doNothing().when(mockRep).deleteSubscription(removeSubscriptionId);

        service.removeSubscription(removeSubscriptionId);
        Mockito.verify(mockRep).deleteSubscription(removeSubscriptionId);
    }

    @Test
    public void getSubscriptionById(){
        Subscription expected = new Subscription(1, "Hello", 300, 2.1d, Period.ofDays(2), 24);
        Mockito.when(mockRep.getSubscriptionByID(1)).thenReturn(expected);

        Subscription actual = service.getSubscriptionById(1);
        assertEquals(expected, actual);
    }
}
