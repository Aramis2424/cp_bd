package model;

import exception.InvalidArgumentException;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int jobFID;
    private int parkingFID;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, int jobFID, int parkingFID) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobFID = jobFID;
        this.parkingFID = parkingFID;
    }

    public static Employee createEmployeeModel(String firstName, String lastName, int jobFID, int parkingFID) {
        validateArguments(firstName, lastName, jobFID,parkingFID);
        return new Employee(-1, firstName, lastName, jobFID, parkingFID);
    }

    public static void validateArguments(String firstName, String lastName, int jobFID, int parkingFID) {
        if (firstName.isBlank())
            throw new InvalidArgumentException("Blank first name");
        if (lastName.isBlank())
            throw new InvalidArgumentException("Blank last name");
        if (jobFID < 0)
            throw new InvalidArgumentException("Negative job id");
        if (parkingFID < 0)
            throw new InvalidArgumentException("Negative job id");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
