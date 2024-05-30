package service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import model.Tariff;
import irepository.ITariffRepository;
@ExtendWith(MockitoExtension.class)
public class TariffServiceTest {
    @Mock
    private ITariffRepository mockRep;

    @InjectMocks
    private TariffService service;

    @Test
    public void createTariff(){
        Tariff newTariff = new Tariff(1, "Sport", 2, 1);
        Mockito.when(mockRep.insertTariff(newTariff)).thenReturn(1);

        service.createTariff(newTariff);
        assertEquals(1, newTariff.getId());
    }

    @Test
    public void updateTariff(){
        Tariff updatingTariff = new Tariff(5, "Teacher", 6, 3);
        Mockito.doNothing().when(mockRep).updateTariff(updatingTariff);

        service.updateTariff(updatingTariff);

        Mockito.verify(mockRep).updateTariff(updatingTariff);
    }

    @Test
    public void remoTariff(){
        int removingTariffId = 1;
        Mockito.doNothing().when(mockRep).deleteTariff(removingTariffId);

        service.removeTariff(removingTariffId);
        Mockito.verify(mockRep).deleteTariff(removingTariffId);
    }

    @Test
    public void getTariffId(){
        Tariff expected = new Tariff(5, "Teacher", 6, 3);
        Mockito.when(mockRep.getTariffById(5)).thenReturn(expected);

        Tariff actual = service.getTariffByID(5);
        assertEquals(expected, actual);
    }
}
