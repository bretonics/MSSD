/**
* Utils.java
* This is a Utils class

* @author  Andres Breton
* @version 1.0
* Postcondition: .
*/

import java.util.Arrays;

public class Utils {

    // Convert Modules enum Types to String[]
    public static String [] convertTypes(Modules.Types[] vals) {
        String [] arr;
        arr = Arrays.stream(vals).map(Enum::toString).toArray(String[]::new);
        return arr;
    }
}
