/**
* Prompt.java
* This is a Prompt class showing a request

* @author  Andres Breton
* @version 1.0
* Postcondition: Prompt object created.
*/
import java.util.*;

class Prompt {

    // Instance variables
    String[] options;
    String choice;

    // Default class constructor
    protected Prompt(String[] options) {
        this.options = options;
        this.choice = choice;
    }


    /** CLASS METHODS **/

    /**
    * This method asks users for menu choice.
    * @param NULL
    * @return Integer of choice selected.
    */
    protected Integer askChoice() {
        Scanner input = new Scanner(System.in);
        Integer choice = null;

        // Ask user for numeric menu choice
        Integer numOptions = options.length;
        while ( choice == null || choice <= 0 || choice > numOptions ) {
            int n = 1;
            System.out.println("\nPlease select:\n");
            for (int i = 0; i < numOptions ; i++) {
                System.out.println("\t" + n + ". " + options[i]);
                n++;
            }
            System.out.println("");
            choice = input.nextInt(); // get user input
        }
        return choice;
    }


}
