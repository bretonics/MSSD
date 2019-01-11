/**
* Employee.java
* This is a Employee class

* @author  Andres Breton
* @version 1.0
* Postcondition: .
*/

import java.util.*;
import java.text.NumberFormat;


class Employee {
    enum Position { CEO, CTO, CFO, MANAGER, ASSOCIATE; }
    enum Location { HEADQUARTERS, NORTH, SOUTH, EAST, WEST, GERMANY, CHINA, MEXICO, REMOTE; }

    /** ----- INSTANCE ----- **/

    // Instance variables
    String name;
    String bday;
    String email;
    Position position;
    String title;
    Integer salary;
    Location location;

    // Default class constructor
    protected Employee() { };

    protected Employee(String name, String bday, String email, String position, String title, Integer salary, String location) {
        this.name = name;
        this.bday = bday;
        this.email = email;
        this.position = Position.valueOf(position);
        this.title = title;
        this.location = Location.valueOf(location);
        this.salary = salary;
    }


    /** ----- CLASS METHODS ----- **/

    /**
    * This method adds a new employee from prompted questions.
    * @param
    * @return void.
    * Postcondition: new Employee object instantiaed with values.
    */
    protected void newHire() {
        Scanner input = new Scanner(System.in);
        String answer = null;

        System.out.println("\nPlease enter new employee information.\n");

        System.out.print("Name: ");
        answer = input.nextLine();
        this.name = answer;

        while(true) {
            System.out.print("Birthday: ");
            answer = input.nextLine();
            if( Utils.checkDate(answer) ) { break; }
        }
        this.bday = answer;

        System.out.print("Email: ");
        answer = input.nextLine();
        this.email = answer;

        while(true) {
            System.out.print("Position: ");
            answer =  input.nextLine().toUpperCase();
            boolean response = setPostion(answer);
            if (response) {
                this.position =  Position.valueOf(answer);
                break;
            }
        }

        System.out.print("Title: ");
        answer = input.nextLine();
        this.title = answer;

        while(true) {
            System.out.print("Location: ");
            answer =  input.nextLine().toUpperCase();
            boolean response = setLocation(answer);
            if (response) {
                this.location =  Location.valueOf(answer);
                break;
            }
        }

    }

    /**
    * This method checks Position enum for value passed.
    * @param String position.
    * @return boolean.
    */
    private boolean setPostion(String position) {

        try {
            Position.valueOf(position) ;
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    /**
    * This method checks Location enum for value passed.
    * @param String location.
    * @return boolean.
    */
    private boolean setLocation(String location) {

        try {
            Location.valueOf(location) ;
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    /**
    * This method updates employee field passed.
    * @param
    * @return void.
    */
    private void updateRecord(){
        // TODO: implement method in employee to update passed record
    }

    /**
    * This method returns the employee's salary.
    * @param null
    * @return salary.
    */
    protected Integer getSalary() { return salary; }

    /**
    * This method prints the employee's salary.
    * @param null
    * @return salary.
    */
    protected void printSalary() {
        NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
        String s = currency.format(salary);
        System.out.println(name + "'s salary is " + s);
    }

    /**
    * This method replaces default Employee object string representation.
    * @param null
    * @return String representation of object.
    */
    public String toString() {
        String out = "\nName: " + name + "" ;
        out += "\nBirth date: "+  bday;
        out += "\nEmail: " + email;
        out += "\nPosition: " + position;
        out += "\nTitle: " + title;
        out += "\nSalary: " + salary;
        out += "\nLocation: " + location;
        out += "\n\n";
        return out;
    }

}
