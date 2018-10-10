/**
* ERP.java
* This is a ERP class
* @author  Andres Breton
* @version 1.0
* Postcondition: Instantiate ERP module.
*/

import java.io.*;

public class ERP {

    /**
    * This is the main method controlling the ERP application.
    * @param args Unused.
    * @return Void.
    * @exception IOException On input error.
    * @see IOException
    **/
    public static void main(String[] args) throws IOException {

        Menus welcome = new Menus("welcome");
        welcome.printMenu();

        while (true) {

            // ERP module services
            Modules modules = new Modules();
            modules.init();
            System.out.println(modules.module);
            modules.launch();

        }

    } // ----------------------------- END OF MAIN -----------------------------
}
