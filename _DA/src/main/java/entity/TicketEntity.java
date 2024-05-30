package entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="tickets")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketID")
    private int ticketID;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "finish_date")
    private LocalDateTime finishDate;
    @Column(name = "parkingFID")
    private int parkingFID;
    @Column(name = "auto_ownerFID")
    private int autoOwnerFID;
    @Column(name = "is_active")
    private Boolean isActive;

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
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
