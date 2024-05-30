package model;

import exception.InvalidArgumentException;

public class Job {
    private int id;
    private String title;
    private int salary;

    public Job() {
    }

    public Job(int id, String title, int salary) {
        this.id = id;
        this.title = title;
        this.salary = salary;
    }

    public static Job createJobModel(String title, int salary) {
        validateArguments(title, salary);
        return new Job(-1, title, salary);
    }

    public static void validateArguments(String title, int salary) {
        if (title.isBlank())
            throw new InvalidArgumentException("Blank title");
        if (salary <= 0)
            throw new InvalidArgumentException("Negative salary");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
