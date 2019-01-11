/**
* Module.java
* This is a Module class

* @author  Andres Breton
* @version 1.0
* Postcondition: .
*/

class Modules {
    enum Types { HR, ACCOUNTS, MARKETING };

    /** ----- INSTANCE ----- **/

    // Instance variables
    String module;
    Types[] types;

    // Default class constructor
    protected Modules() {
        this.types = Types.values();
    };


    /** ----- CLASS METHODS ----- **/

    /**
    * This method initiates Modules, prompting menu for choice selection.
    * @param null
    * @return void.
    */
    protected void init(){
        String[] types = Utils.convertTypes(Modules.Types.values());
        Menus menu = new Menus("modules");
        Prompt prompt = new Prompt( menu.getMenu() );
        Integer choice = prompt.askChoice();
        setModule(types[choice-1]);  // set module selected in class
    }

    /**
    * This method launches ERP module class.
    * @param null
    * @return void.
    */
    protected void launch() {
        System.out.println("\nLaunching " + module + "...\n");
        switch(module) {
            case "HR":
                HR.main(); break;
            case "ACCOUNTS":
                Accounts.main(); break;
            case "MARKETING":
                Marketing.main(); break;
        }
    }

    /**
    * This method sets module for Modules instance.
    * @param String module name
    * @return void.
    */
    protected void setModule(String module) {
        this.module = module.toUpperCase();
    }

    /**
    * This method gets all available modules.
    * @param null
    * @return Types[] array of enum Types.
    */
    protected Types[] getModules() {
        return Types.values();
    }

}
