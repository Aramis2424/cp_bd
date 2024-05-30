package model;

import exception.InvalidArgumentException;

import java.time.LocalDateTime;

public class Booking {
    private int id;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private int parkingFID;
    private int autoOwnerFID;
    private Boolean isActive;

    public Booking() {
    }

    public Booking(int id, LocalDateTime startDate, LocalDateTime finishDate, int parkingFID, int autoOwnerFID) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.parkingFID = parkingFID;
        this.autoOwnerFID = autoOwnerFID;
        this.isActive = Boolean.TRUE;
    }

    public static Booking createBookingModel(LocalDateTime finishDate, int parkingFID, int autoOwnerFID) {
        validateArguments(finishDate, parkingFID, autoOwnerFID);
        return new Booking(-1, LocalDateTime.now(), finishDate, parkingFID, autoOwnerFID);
    }

    public static void validateArguments(LocalDateTime finishDate, int parkingFID, int autoOwnerFID) {
        if (finishDate.isBefore(LocalDateTime.now()))
            throw new InvalidArgumentException("Finish parking date before current date");
        if (parkingFID < 0)
            throw new InvalidArgumentException("Negative parking Id");
        if (autoOwnerFID < 0)
            throw new InvalidArgumentException("Negative autoOwner Id");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public int getParkingFID() {
        return parkingFID;
    }

    public void setParkingFID(int parkingFID) {
        this.parkingFID = parkingFID;
    }

    public int getAutoOwnerFID() {
        return autoOwnerFID;
    }

    public void setAutoOwnerFID(int autoOwnerFID) {
        this.autoOwnerFID = autoOwnerFID;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
