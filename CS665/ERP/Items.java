/**
* Items.java
* This is a Items class containing all available items.

* @author  Andres Breton
* @version 1.0
* Postcondition: .
*/

import java.util.*;

class Items {

    enum Materials { BRUSH, NYLON, PCV, WAX, DYE, PLASTESIZER, MOLD, BATTERY, CAPS };

    static List<String> PROMO1 = Arrays.asList("Quip Pro I", "Quip Mini", "Quipi II");
    static List<String> PROMO2 = Arrays.asList("Quipper", "Quipo", "Quipi I");
    static List<String> PROMO3 = Arrays.asList("Quip Pro Advanced", "Quipster", "Quipi III");

    /** ----- CLASS METHODS ----- **/

    protected Materials[] getMaterials() {
        return Materials.values();
    }

    protected void displayMaterials() {
        String[] materials = Utils.convertTypes(Items.Materials.values());
        for (String m : materials) {
            System.out.println(m);
        }
    }

    protected static List<String> getItems(String list) {
        List<String> items = null;
        switch(list.toUpperCase()) {
            case "PROMO1":
                items = PROMO1;
                break;
            case "PROMO2":
                items = PROMO2;
                break;
            case "PROMO3":
                items = PROMO3;
                break;
            default:
                System.out.println("Sorry no list found for '" + list + "'.");
        }
        return items;
    }

}
