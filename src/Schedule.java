import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import java.util.ArrayList;

/**
 * Created by RobbieZhuang on 2016-10-31.
 */
public class Schedule {

    private static void readHours() throws FileNotFoundException {

        System.out.println("Reading hours from text file");

        // Creating variables
        String str;
        int day;
        int inHour = 0;
        int outHour = 0;
        int demand;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Schedule.txt")));
            while ((str = br.readLine()) != null) {

                // Reading the file
                if (str.length() == 1) {
                    day = determineDay(str);
                } else {
                    inHour = determineHour(str);
                    outHour = determineHour(str.substring(str.indexOf("-") + 1));
                    demand = Integer.parseInt(str.substring(str.length() - 2));
                }

                // Adding hours and demand to program
                for (int i = inHour; i < outHour; i++) {
                    // Add demand to each time slot

                }

            }
        } catch (Exception e) {
            System.out.println("*** Something wrong with the buffered reader");
        }


    }

    private static int determineHour(String str) {

        int hour;
        str = str.substring(0, 2);

        try {
            hour = Integer.parseInt(str);
        } catch (Exception e) {
            System.out.println("*** Something wrong with hour converter");
        }

        return 0; ///
    }

    private static int determineDay(String str) {

        if (str.substring(0, 1).equalsIgnoreCase("M")) {
            return 1;
        } else if (str.substring(0, 1).equalsIgnoreCase("T")) {
            return 2;
        } else if (str.substring(0, 1).equalsIgnoreCase("W")) {
            return 3;
        } else if (str.substring(0, 1).equalsIgnoreCase("R")) {
            return 4;
        } else if (str.substring(0, 1).equalsIgnoreCase("F")) {
            return 5;
        } else if (str.substring(0, 1).equalsIgnoreCase("S")) {
            return 6;
        } else if (str.substring(0, 1).equalsIgnoreCase("U")) {
            return 7;
        }

        System.out.println("*** determineDay");
        return 0;
    }

    // Our take on building a 2d array of ARRAYLISTS, Timeslot is the individual ArrayList that holds Employee objects
    Timeslot[][] table;

    // Numnber of employees required at each 1h block
    int[][] requiredEmployees = new int[7][24];

    // Employees
    ArrayList<Employee> allEmployees = new ArrayList<Employee>();
    ArrayList<Employee> allWorkers = new ArrayList<Employee>();

    // Build constructor for a schedule
    // The schedule keeps track of how many people are in each 1h block
    public Schedule() {
        table = new Timeslot[7][24];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = new Timeslot(requiredEmployees[i][j]);
            }
        }
    }

    public void dumpEmployees() {
        for (Employee e : allEmployees) {
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 24; j++) {
                    if (e.getHoursAvailable()[i][j]) {
                        table[i][j].addEmployee(e);
                        e.addHourOfWork();
                    }
                }
            }
        }
    }

    public void optimizeEmployees() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j].currentNumberOfEmployees() > table[i][j].getRequiredEmployees()) {
                    int max = 0;
                    Employee x = new Employee();
                    for (Employee e : table[i][j].getEmployeesAtSlot()) {
                        // If e is not a manager
                        if (!(e instanceof Manager) && e.getTotalHours() > max) {
                            x = e;
                        }
                    }
                    table[i][j].getEmployeesAtSlot().remove(x);
                }
            }
        }
    }
}