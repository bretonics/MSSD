/**
* Invoice.java
* This is a Invoice class .
* @author  Andres Breton
* @version 1.0
* Postcondition: .
*/

import java.util.Random;

class Invoice {

    /** ----- INSTANCE ----- **/

    // Instance variables
    private static Invoice invoice;
    String bill;
    String status = "Pending";
    Integer number;

    // Default private class constructor to force use of public method for instantiation (Singleton)
    private Invoice() {
        this.number = new Random().nextInt();
        this.status = status;
    }


    /** ----- CLASS METHODS ----- **/

    /**
    * This method creates Invoice instance.
    * @param null
    * @return Invoice.
    */
    public static synchronized Invoice getInstance() {
        if (invoice == null) {
            invoice = new Invoice();
        }
        return invoice;
    }

    /**
    * This method creates the bill.
    * @param null.
    * @return void.
    */
    protected void createBill() {
        String bill = "\n__________________________________________________________\n";
               bill += "| " + ERP.company.toUpperCase( ) + "                                                   |\n";
               bill += "|                                                        |\n";
               bill += "|  Invoice #" + number + " (" + status +")                         |\n";
               bill += "|                                                        |\n";
               bill += "|   Item 1" + "          $1,000                               |\n";
               bill += "|   Item 2" + "          $345                                 |\n";
               bill += "|   Item 3" + "          $655                                 |\n";
               bill += "|   Item 4" + "          $2,500                               |\n";
               bill += "|                                                        |\n";
               bill += "|   TOTAL           $4,500                               |\n";
               bill += "|                                                        |\n";
               bill += "|                                                        |\n";
               bill += "|   Please pay the amount above by 12/01.                |\n";
               bill += "|________________________________________________________|\n";
        this.bill = bill;
    }

    /**
    * This method returns bill.
    * @param String
    * @return String representation of bill.
    */
    protected String getBill() { return bill; }

    /**
    * This method prints bill.
    * @param void
    * @return String representation of bill.
    */
    protected void printBill() { System.out.println(bill); }

    /**
    * This method sets the invoice status.
    * @param String status (settled, pending, etc)
    * @return void.
    */
    public void setStatus(String status) {
        this.status = status;
    }

}
