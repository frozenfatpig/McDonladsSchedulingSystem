/*
 Manager.java
 This is an object for the manager
 Managers are an extension to employee

 Created by Bill Li on 2016-10-30.
 */

public class Manager extends Employee{

    // Setting up variables
    private double salary;

    // Default constructor for manager
    Manager() {
        this.setEmployeeType("Manager");
    }

    public double getPay() {
        return salary;
    }

    public void setPay(double salary) {
        this.salary = salary;
    }
}
