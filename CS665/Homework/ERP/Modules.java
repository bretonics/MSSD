/**
* Module.java
* This is a Module class

* @author  Andres Breton
* @version 1.0
* Postcondition: .
*/

import java.util.Arrays;

class Modules {
    enum Types { HR, ACCOUNTS, MARKETING };

    // Instance variables
    String module;
    Types[] types;

    // Default class constructor
    protected Modules() {
        this.types = Types.values();
    };

    // Class constructor
    // private Modules(String module) {
    //     this.module = module.toUpperCase();
    // }


    /** CLASS METHODS **/
    //

    protected void init(){
        String[] types = convert();
        Prompt prompt = new Prompt(types);
        Integer choice = prompt.askChoice();
        setModule(types[choice-1]);
    }

    protected void setModule(String module) {
        this.module = module;
    }

    // Get all available modules
    protected Types[] getModules() {
        return Types.values();
    }

    //


    // Launch ERP module selected
    protected void launch() {
        System.out.println("\nLaunching " + module + "...\n");
        switch(module) {
            case "HR":
                HR.main(); break;
            // case "ACCOUNTS":
            //     Accounts.main(); break;
            // case "MARKETING":
            //     Marketing.main(); break;
        }
    }

    private String [] convert() {
        String [] arr;
        arr = Arrays.stream(Types.values()).map(Enum::toString).toArray(String[]::new);
        return arr;
    }
}
