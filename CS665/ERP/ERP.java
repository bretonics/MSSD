/**
* ERP.java
* This is a ERP class
* @author  Andres Breton
* @version 1.0
* Postcondition: Instantiate ERP module.
*/

import java.io.*;

public class ERP {

    // Company name
    public static String company = "Quip";

    // Initialize database
    public static Database DB = new Database();


    /**
    * This is the main method controlling the ERP application and its modules.
    * @param args Unused.
    * @return Void.
    * @exception IOException On input error.
    * @see IOException
    **/
    public static void main(String[] args) throws IOException {

        Menus welcome = new Menus("welcome");
        System.out.println(welcome);

        // Keep alive, come back to main menu
        while (true) {
            // ERP module services
            Modules modules = new Modules();
            modules.init();
            modules.launch();
        }

    } // ----------------------------- END OF MAIN -----------------------------
}
