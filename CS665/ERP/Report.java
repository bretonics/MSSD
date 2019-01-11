/**
* Report.java
* This is a Report class .
* @author  Andres Breton
* @version 1.0
* Postcondition: .
*/

import java.util.*;

class Report {

    /** ----- INSTANCE ----- **/

    // Instance variables
    private static Report report;
    List<String> items = new ArrayList<String>();

    // Default private class constructor to force use of public method for instantiation (Singleton)
    protected Report() { this.items = items; }

    /** ----- CLASS METHODS ----- **/

    /**
    * This method creates Invoice instance.
    * @param null
    * @return Report.
    */
    public static synchronized Report getInstance() {
        if (report == null) {
            report = new Report();
        }
        return report;
    }

    /**
    * This method adds items to the report.
    * @param String item
    * @return void.
    */
    protected void addItem(String item) {
        items.add(item);
    }

    /**
    * This method replaces default Report object string representation.
    * @param null
    * @return String representation of object.
    */
    public String toString() {
        String out = "\n--------------------------------------------------------\n";
               out += "|\n";
               out += "|\n";
               out += "|   EXPENSE REPORT\n";
               out += "|\n";
               out += "|   Items:\n";
               out += "|\n";

        for (String item : items ) {
            out += "|     " + item + "\n";

        }
        out += "|\n";
        out += "|\n";
        out += "--------------------------------------------------------\n";
        return out;
    }
}
