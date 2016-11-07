/*
 Worker.java
 This creates an object for a worker
 Worker is an extension of employee

 Created by Bill Li on 2016-10-30.
 */

public class Worker extends Employee {

    // Setting up variables
    private double wage;

    // Default constructor for worker
    Worker() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 24; j++) {
                this.setHoursAvailable(i, j, false);
            }
        }
    }

    public double getPay() {
        return wage;
    }

    public void setPay(double wage) {
        this.wage = wage;
    }
}
