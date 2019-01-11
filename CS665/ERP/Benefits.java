/**
* Benefits.java
* This is a Benefits class with all the company benefits.
* @author  Andres Breton
* @version 1.0
*/


class Benefits {
    enum Benefit { HEALTH, DENTAL, VISION, PTO, RETIREMENT, BLI, LTD; }

    /** ----- INSTANCE ----- **/

    // Instance variables
    Benefit[] benefits;

    // Default class constructor
    Benefits() {
        this.benefits = Benefit.values();
    }

    /** ----- CLASS METHODS ----- **/

    /**
    * This method returns all benefits.
    * @param null
    * @return .
    */
    protected void getBenefits() {
        int idx = 1;
        System.out.println("\nList of available benefits: \n");
        for(Benefit b : this.benefits) {
            System.out.println(idx++ + ". " + b);
        }
    }
}
