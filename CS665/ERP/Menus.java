/**
* Menus.java
* This is a Menus class

* @author  Andres Breton
* @version 1.0
* Postcondition: .
*/


class Menus {

    /** ----- INSTANCE ----- **/

    // Instance variables
    String[] menu;

    // Default class constructor
    Menus(String menu) {
        switch (menu) {
            case "welcome":
                this.menu = welcome; break;
            case "modules":
                this.menu = modules; break;
            case "hr":
                this.menu = hr; break;
            case "accounts":
                this.menu = accounts; break;
            case "marketing":
                this.menu = marketing; break;
        }
    }

    /** ----- CLASS VARIABLES ----- **/

    static String company = ERP.company;

    // -- Different types of menus (array of strings, line per array element)
    String[] welcome = { "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n",
                         "     Welcome! Entering the ERP system for " + company + ".\n",
                         "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",
                       };

    String[] modules = Utils.convertTypes(Modules.Types.values());

    String[] hr = { "Enter new employee profile\n",
                    "Get employee information\n",
                    "Modify employee\n",
                    "See benefit offerings\n",
                    "Organization overview\n",
                    "Report issue\n",
                    };

    String[] accounts = { "Generate expense report\n",
                          "View expense report\n",
                          "Generate invoice\n",
                          "View invoice\n",
                          "Settle invoice\n",
                          "Generate paycheck\n",
                          "View paycheck\n",
                          "View employee salary\n",
                        };

    String[] marketing = { "Create campaign\n",
                           "Marketing items\n",
                           "See all campaigns\n",
                           "Modify campaign\n",
                         };

    /** ----- CLASS METHODS ----- **/

    /**
    * This method returns menu of Menus instance.
    * @param null
    * @return String[] array of menu lines.
    */
    protected String[] getMenu() {
        return menu;
    }

    /**
    * This method replaces default Menus object string representation.
    * @param null
    * @return String representation of object.
    */
    public String toString() {
        int n = 1;
        String out = "\n";
        for (String line: menu) {
            out += line + "\n";
        }
        out += "\n";
        return out;
    }

}
