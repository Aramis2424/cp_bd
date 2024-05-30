package tui.view;

import model.Parking;

import java.util.List;
import java.util.Map;

public class ParkingView extends View {
    public void printAllParkings(Map<Parking, String> parkings) {
        int i = 1;
        for (Map.Entry<Parking, String> parking : parkings.entrySet()) {
            System.out.printf("%d) Адрес: %s\n\tМест всего: %d\n\tМест свободно: %d\n\tДействующий тариф: %s\n",
                    i++, parking.getKey().getAddress(), parking.getKey().getTotalParkingPlaces(),
                    parking.getKey().getFreeParkingPlaces(), parking.getValue());
        }
    }

    public void printAllParkings(int index, Parking parking, String street) {
        System.out.printf("%d) Адрес: %s\n\tМест всего: %d\n\tМест свободно: %d\n\tДействующий тариф: %s\n",
                index, parking.getAddress(), parking.getTotalParkingPlaces(),
                parking.getFreeParkingPlaces(), street);
    }
}
