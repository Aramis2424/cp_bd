package entity;

import javax.persistence.*;

@Entity
@Table(name="parkings")
public class ParkingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parkingID")
    private int parkingID;
    @Column(name = "address")
    private String address;
    @Column(name = "count_total_places")
    private int totalParkingPlaces;
    @Column(name = "count_free_places")
    private int freeParkingPlaces;
    @Column(name = "tariffFID")
    private int tariffFID;

    public int getParkingID() {
        return parkingID;
    }

    public void setParkingID(int parkingID) {
        this.parkingID = parkingID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalParkingPlaces() {
        return totalParkingPlaces;
    }

    public void setTotalParkingPlaces(int totalParkingPlaces) {
        this.totalParkingPlaces = totalParkingPlaces;
    }

    public int getFreeParkingPlaces() {
        return freeParkingPlaces;
    }

    public void setFreeParkingPlaces(int freeParkingPlaces) {
        this.freeParkingPlaces = freeParkingPlaces;
    }

    public int getTariffFID() {
        return tariffFID;
    }

    public void setTariffFID(int tariffFID) {
        this.tariffFID = tariffFID;
    }
}
