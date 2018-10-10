/**
* Menus.java
* This is a Menus class

* @author  Andres Breton
* @version 1.0
* Postcondition: .
*/

class Menus {

    // Instance variables
    String[] menu;

    // Class variables
    String[] welcome = { "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n",
                         "     Welcome! Entering the ERP system for Company.\n",
                         "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",
                       };

    String[] main = { "Enter new employee profile\n",
                      "Get employee information\n",
                      "Modify employee\n",
                      "See benefit offerings\n",
                      "Organization overview\n",
                      "Report issue\n",
                    };

    // Default class constructor
    Menus(String menu) {
        switch (menu) {
            case "main":
                this.menu = main; break;
            case "welcome":
                this.menu = welcome; break;
            default:
                this.menu = main; break;
        }
    }


    /** CLASS METHODS **/

    // Get menu type array
    protected String[] getMenu() {
        return menu;
    }

    // Print menu
    protected void printMenu() {
        int n = 1;
        System.out.println("");
        for (String line: menu) {
            System.out.println(line);
        }
        System.out.println("");
    }
}
