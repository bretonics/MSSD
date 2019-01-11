/**
* Paycheck.java
* This is a Paycheck class .
* @author  Andres Breton
* @version 1.0
* Postcondition: .
*/

class Paycheck {

    /** ----- INSTANCE ----- **/

    // Instance variables
    private static Paycheck paycheck;
    String check;

    // Default private class constructor to force use of public method for instantiation (Singleton)
    private Paycheck() {}


    /** ----- CLASS METHODS ----- **/

    /**
    * This method creates Paycheck instance.
    * @param null
    * @return Paycheck.
    */
    public static synchronized Paycheck getInstance() {
        if (paycheck == null) {
            paycheck = new Paycheck();
        }
        return paycheck;
    }

    /**
    * This method creates the check.
    * @param Employee and Integer amount for check.
    * @return void.
    */
    protected void createCheck(Employee e, Integer amount) {
        String check = "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
               check += "| " + ERP.company + "                                             101   |\n";
               check += "|                                                        |\n";
               check += "|  Paid to the                                           |\n";
               check += "|  order of         _____ " + e.name + " _______________ $" + amount + "   |\n";
               check += "|                                                        |\n";
               check += "|                                                        |\n";
               check += "|   :01340501:30430530450                                |\n";
               check += "|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n";
        this.check = check;
    }

    /**
    * This method returns check.
    * @param String
    * @return String representation of check.
    */
    protected String getCheck() { return check; }

    /**
    * This method prints check.
    * @param void
    * @return String representation of check.
    */
    protected void printCheck() { System.out.println(check); }
}
