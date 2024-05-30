package entity;

import javax.persistence.*;

@Entity
@Table(name="parking_meters")
public class ParkingMeterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parking_meterID")
    private int parkingMeterID;
    @Column(name = "gate_number")
    private int gateNumber;
    @Column(name = "parkingFID")
    private int parkingFID;

    public int getParkingMeterID() {
        return parkingMeterID;
    }

    public void setParkingMeterID(int parkingMeterID) {
        this.parkingMeterID = parkingMeterID;
    }

    public int getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(int gateNumber) {
        this.gateNumber = gateNumber;
    }

    public int getParkingFID() {
        return parkingFID;
    }

    public void setParkingFID(int parkingFID) {
        this.parkingFID = parkingFID;
    }
}
