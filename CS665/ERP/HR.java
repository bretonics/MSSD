/**
* HR.java
* This is a HR class
* @author  Andres Breton
* @version 1.0
* Postcondition: .
*/
import java.io.*;
import java.util.*;

public class HR {

    public static void main() {

        // Class Database object initiated from ERP
        Database DB = Database.getInstance();

        // Initialize objects
        Employee employee = new Employee();
        Benefits benefits = new Benefits();

        // Get main menu and display to user for input
        Menus main = new Menus("hr");
        String[] items = main.getMenu();
        Prompt prompt = new Prompt( items );  // create Prompt instance with menu
        Integer choice = prompt.askChoice();  // ask user input from main menu Prompt

        // Delegate user action desired
        switch(choice) {
            case 1:  // Enter new employee profile
                employee.newHire();
                DB.EMPLOYEEDB.add(employee);
                break;
            case 2:  // Get employee information
                employee = DB.getEmployee();
                System.out.println(employee);
                break;
            case 3:  // Modify employee
                DB.updateRecord();
                break;
            case 4:   // See benefit offerings
                benefits.getBenefits();
                break;
            case 5:  // Organization overview
                DB.printEmployees();
                break;
            case 6:   // Report issue
                DB.addIssue();
                break;
            default:
                prompt.askChoice();  // prompt again if choice not found
        }
    } // ----------------------------- END OF MAIN -----------------------------

}
