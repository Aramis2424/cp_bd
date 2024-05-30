package service;

import irepository.ICarRepository;
import model.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @Mock
    private ICarRepository mockRep;

    @InjectMocks
    private CarService service;

    @Test
    public void createCar(){
        Car newCar = new Car("232", "ferrari", 1);
        Mockito.doNothing().when(mockRep).insertCar(newCar);

        service.createCar(newCar);

        Mockito.verify(mockRep).insertCar(newCar);
    }

    @Test
    public void updateCar(){
        Car updatingCar = new Car("213", "mercedes", 1);
        Mockito.doNothing().when(mockRep).updateCar(updatingCar);

        service.updateCar(updatingCar);

        Mockito.verify(mockRep).updateCar(updatingCar);
    }

    @Test
    public void removeCar(){
        String number = "213";
        Mockito.doNothing().when(mockRep).deleteCar(number);

        service.removeCar(number);

        Mockito.verify(mockRep).deleteCar(number);
    }

    @Test
    public void getCarByNumber(){
        String number = "A123BB";
        Car expected = new Car(number, "Electric", 1);

        Mockito.when(mockRep.getCarByNumber(number)).thenReturn(expected);

        Car actual = service.getCarByNumber(number);

        Mockito.verify(mockRep).getCarByNumber(number);
        assertEquals(expected, actual);
    }

    @Test
    void getCarsByUser() {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("a111aa", "ICE", 1));
        carList.add(new Car("b222bb", "Electric", 1));
        int userId = 1;

        Mockito.when(mockRep.getCarsByUser(userId)).thenReturn(carList);

        List<Car> actualCarList = service.getCarsByUser(userId);

        assertArrayEquals(carList.toArray(), actualCarList.toArray());
    }
}
