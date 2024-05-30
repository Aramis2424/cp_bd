package model;

import exception.InvalidArgumentException;

import java.time.LocalDateTime;

public class Ticket {
    private int id;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private int parkingFID;
    private int autoOwnerFID;
    private Boolean isActive;

    public Ticket() {
    }

    public Ticket(int id, LocalDateTime startDate, LocalDateTime finishDate, int parkingFID, int autoOwnerFID) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.parkingFID = parkingFID;
        this.autoOwnerFID = autoOwnerFID;
        this.isActive = Boolean.TRUE;
    }

    public static Ticket createTicketModel(int parkingFID, int autoOwnerFID) {
        validateArguments(parkingFID, autoOwnerFID);
        return new Ticket(-1, LocalDateTime.now(), null, parkingFID, autoOwnerFID);
    }

    public static void validateArguments(int parkingFID, int autoOwnerFID) {
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
