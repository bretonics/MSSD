/**
* Utils.java
* This is a Utils class

* @author  Andres Breton
* @version 1.0
* Postcondition: .
*/

import java.util.*;
import java.text.*;

public class Utils {

    /**
    * This method converts Modules enum Types to String[] array.
    * @param NULL
    * @return String[] array of Module Types.
    */
    public static String[] convertTypes(Modules.Types[] vals) {
        String [] arr;
        arr = Arrays.stream(vals).map(Enum::toString).toArray(String[]::new);
        return arr;
    }

    /**
    * This method converts Items enum Materials to String[] array.
    * @param NULL
    * @return String[] array of Module Items.
    */
    public static String[] convertTypes(Items.Materials[] vals) {
        String [] arr;
        arr = Arrays.stream(vals).map(Enum::toString).toArray(String[]::new);
        return arr;
    }


    /**
    * This method converts Campaign enum CampaignType to String[] array.
    * @param NULL
    * @return String[] array of Module Items.
    */
    public static String[] convertTypes(Campaign.CampaignType[] vals) {
        String [] arr;
        arr = Arrays.stream(vals).map(Enum::toString).toArray(String[]::new);
        return arr;
    }

    /**
    * This method checks date string passed for correct format (MM-dd-yyyy).
    * @param String dbay.
    * @return boolean.
    */
    public static boolean checkDate(String bday) {
        Date date;
        SimpleDateFormat DateFormat = new SimpleDateFormat ("MM-dd-yyyy");
        try {
            date = DateFormat.parse(bday);
            return true;
        } catch (ParseException e) {
            System.out.println("Unparseable Birth date format (Need: " + DateFormat.toPattern() + ")");
            return false;
        }
    }

}
