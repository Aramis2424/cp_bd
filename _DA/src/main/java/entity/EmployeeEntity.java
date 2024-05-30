package entity;

import javax.persistence.*;

@Entity
@Table(name="employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeID")
    private int employeeID;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "jobFID")
    private int jobFID;
    @Column(name = "parkingFID")
    private int parkingFID;

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getJobFID() {
        return jobFID;
    }

    public void setJobFID(int jobFID) {
        this.jobFID = jobFID;
    }

    public int getParkingFID() {
        return parkingFID;
    }

    public void setParkingFID(int parkingFID) {
        this.parkingFID = parkingFID;
    }
}
