/**
* Accounts.java
* This is a Accounts class .
* @author  Andres Breton
* @version 1.0
* Postcondition: .
*/

import java.util.*;

public class Accounts {

    public static void main() {
        // Class Database object initiated from ERP
        Database DB = Database.getInstance();

        // Initialize objects
        Employee employee = new Employee();
        Paycheck paycheck = Paycheck.getInstance();
        Invoice invoice = Invoice.getInstance();

        // Get main menu and display to user for input
        Menus menu = new Menus("accounts");
        String[] items = menu.getMenu();
        Prompt prompt = new Prompt( items );  // create Prompt instance with menu
        Integer choice = prompt.askChoice();  // ask user input from main menu Prompt

        // Delegate user action desired
        switch(choice) {
            case 1:  // Generate expense report
                generateExpenseReport();
                break;
            case 2:  // View expense report
                for(Report r : DB.EXPENSESDB) { System.out.println(r); }
                break;
            case 3:  // Generate invoice
                invoice = Invoice.getInstance();
                invoice.createBill();
                invoice.printBill();
                break;
            case 4:  // View invoice
                viewInvoice(invoice);
                break;
            case 5:  // Settle invoice
                if(invoice.getBill() != null) {
                    invoice = settleInvoice(invoice);
                } else {
                    System.out.println("Sorry, no invoice.");
                }
                break;
            case 6:  // Generate paycheck
                employee = DB.getEmployee();
                paycheck.createCheck(employee, 1000);
                paycheck.printCheck();
                break;
            case 7:  // View paycheck
                viewPaycheck(paycheck);
                break;
            case 8:  // View employee salary
                employee = DB.getEmployee();
                employee.printSalary();
                break;
            default:
                prompt.askChoice();  // prompt again if choice not found
        }
    } // ----------------------------- END OF MAIN -----------------------------

    /** ----- CLASS METHODS ----- **/

    /**
    * This method generates a report by asking user what items to add.
    * @param null
    * @return void.
    * Postcondition: generates list of string items for report and adds report to database.
    */
    private static void generateExpenseReport() {
        Database DB = Database.getInstance();
        Report report = Report.getInstance();
        Scanner input = new Scanner(System.in);

        Items i = new Items();
        System.out.println("\nWe have the following materials:\n");
        i.displayMaterials();

        String answer;
        while(true) {
            System.out.print("\nEnter item to add ('exit' to terminate): ");
            answer = input.nextLine();
            if (answer.equals("exit")) { break; }
            report.addItem(answer);
        }
        System.out.println("\n\nReport generated. Saving to database...\n\n");
        DB.EXPENSESDB.add(report);
    }

    /**
    * This method displays paycheck to user.
    * @param Paycheck object.
    * @return void.
    */
    private static void viewPaycheck(Paycheck paycheck) {
        String check = paycheck.getCheck();
        if(check == null) {
            System.out.println("\nSorry. No current paycheck generated.");
        } else {
            System.out.println(check);
        }
    }

    /**
    * This method displays invoice to user.
    * @param Invoice object.
    * @return void.
    */
    private static void viewInvoice(Invoice invoice) {
        String bill = invoice.getBill();
        if(bill == null) {
            System.out.println("\nSorry. No current invoice generated.");
        } else {
            System.out.println(bill);
        }
    }

    /**
    * This method changes the Invoice status to "Settled".
    * @param Invoice object.
    * @return Invoice.
    */
    private static Invoice settleInvoice(Invoice i) {
        System.out.println("Settling invoice:\n" + i.getBill());
        Scanner input = new Scanner(System.in);
        String r;

        System.out.print("Confirm you want to settle this invoice (Y/n): ");
        r = input.next();
        if (r.equals("Y")) {
            i.setStatus("Settled");
            System.out.println("\nInvoice settled. Status is: " + i.status);
        } else {
            System.out.println("\nCancelled.");
        }

        return i;
    }

}
