/**
* Database.java
* This is a Database class simutaling storage of records.
* @author  Andres Breton
* @version 1.0
* Postcondition: .
*/

import java.util.*;

class Database {

    /** ----- CLASS VARIABLES ----- **/

    static String company = ERP.company;
    Scanner input = new Scanner(System.in);

    // Employees database
    static List<Employee> EMPLOYEEDB = new ArrayList<Employee>();
    static Employee andres = new Employee("Andres", "01-01-1999", "andres@" + company + ".com", "CEO", "Chief Executive Officer", 200000, "HEADQUARTERS");
    static Employee mark = new Employee("Mark", "05-21-1989", "mark@" + company + ".com", "CFO", "Chief Financial Officer", 130000, "HEADQUARTERS");
    static Employee tom = new Employee("Tom", "12-11-1999", "tom@" + company + ".com", "ASSOCIATE", "Associate Engineer II", 80000, "REMOTE");

    // Issues Database
    static List<String> ISSUESDB = new ArrayList<String>();

    // Expenses Database
    static List<Report> EXPENSESDB = new ArrayList<Report>();

    // Campaigns Database
    static List<Campaign> CAMPAIGNSDB = new ArrayList<Campaign>();


    /** ----- INSTANCE ----- **/

    // Instance variables
    private static Database database;

    // Default class constructor
    protected Database() {
        // Load dummy data
        EMPLOYEEDB.add(andres);
        EMPLOYEEDB.add(mark);
        EMPLOYEEDB.add(tom);

        this.EMPLOYEEDB = EMPLOYEEDB;
        this.ISSUESDB = ISSUESDB;
        this.EXPENSESDB = EXPENSESDB;
    }


    /** ----- CLASS METHODS ----- **/

    public static synchronized Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    /**
    * This method prints all employees in database.
    * @param null
    * @return void.
    */
    protected void printEmployees() {
        System.out.println("\n-- Listing all employees\n");
        for (Employee employee : EMPLOYEEDB) {
            System.out.println(employee);
        }
    }

    /**
    * This method searches and returns Employee if present in database.
    * @param String name of Employee.
    * @return Employee.
    */
    protected Employee getEmployee() {
        // Employee emp = null;
        String name = null;

        System.out.println("\n-- Searching employee by name\n");
        System.out.print("\nName to search: ");
        name = input.nextLine().toLowerCase();

        // TODO - refactor this to searchRecord()
        for (Employee employee : EMPLOYEEDB) {
            if ( name.equals( employee.name.toLowerCase() ) ) {
                System.out.println("\nFound employee '" + name + "':\n");
                return employee;
            }
        }
        System.out.println("\nSorry! Could not find employee named '" + name + "'.\n");
        return null;
    }

    /**
    * This method adds issues to database.
    * @param null.
    * @return void.
    */
    protected void addIssue() {
        String issue = null;
        System.out.println("\n-- Adding issue\n");
        while(issue == null) {
            System.out.print("\nPlease enter issue: ");
            issue = input.nextLine().toLowerCase();
        }
        ISSUESDB.add(issue);
        System.out.println("Issue successfully added!");
        System.out.println("\t--> " + issue);
    }

    /**
    * This method updates employee record.
    * @param
    * @return void.
    */
    protected void updateRecord() {
        String field = null;

        System.out.println("\n-- Updating employee by name");
        System.out.print("\nEmployee to update: ");
        String name = input.nextLine().toLowerCase();

        Employee employee = searchRecord("EMPLOYEEDB", name);

        if (employee != null) {
            while (field == null) {
                // TODO: implement method in employee to update passed record
                System.out.print("\nField to update [currently 'email' field only]: ");
                field = input.nextLine().toLowerCase();
            }
            System.out.print("\nEnter new value for " + field + ": ");
            employee.email = input.nextLine();

            System.out.println("\nEmployee updated!\n" + employee);
        }
    }

    /**
    * This method prints all campaigns in database.
    * @param null
    * @return void.
    */
    protected void printCampaigns() {
        System.out.println("\n-- Listing all campaigns\n");
        for (Campaign campaign : CAMPAIGNSDB) {
            System.out.println(campaign);
        }
    }

    /**
    * This method searches and returns Campaign if present in database.
    * @param String name of Campaign.
    * @return Campaign.
    */
    protected Campaign getCampaign() {
        String name = null;

        System.out.println("\n-- Searching campaign by name\n");
        System.out.print("\nName to search: ");
        name = input.nextLine().toLowerCase();

        for (Campaign campaign : CAMPAIGNSDB) {
            if ( name.equals( campaign.name.toLowerCase() ) ) {
                System.out.println("\nFound campaign '" + name + "':\n");
                return campaign;
            }
        }
        System.out.println("\nSorry! Could not find employee named '" + name + "'.\n");
        return null;
    }

    /**
    * This method searches for record in database.
    * @param String name of database and record to lookup.
    * @return Object record.
    */
    private Employee searchRecord(String database, String record) {
        database = database.toUpperCase();
        record = record.toLowerCase();

        switch(database) {
            case("EMPLOYEEDB"):
                for (Employee employee : EMPLOYEEDB) {
                    if ( record.equals( employee.name.toLowerCase() ) ) {
                        System.out.println("\nFound employee '" + record + "':\n" + employee);
                        return employee;
                    }
                }
                System.out.println("\nSorry! Could not find entry '" + record + "' in database" + database + ".\n");
            default:
                return null;
        }
    }

}
