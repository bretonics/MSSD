import java.io.*;
import java.util.*;


public class ArtworkManagement{

    /**
    * This is the main method which makes use of searchArtworks method.
    * This method stores and manages museum artwork records by scanning
    * a file and creating an Artwork object for each line entry (record).
    * Each record is added to an array of Artwork objects to perform search
    * and update operations according to user response. It will display a
    * menu with items (operations) the user can chose from and will
    * appropriately handle the request.
    * @param args Unused.
    * @return Void.
    * @exception IOException On input error.
    * @see IOException
    * Output: a String main menu, Artwork object information, or exit message
    * Postcondition: When this method finishes it either found and displayed
    * the artwork record matching the provided id, update the location of the
    * artwork record marching the provided id and printed the newly updated
    * record, or print an exit message.
    */
    public static void main(String[] args) throws IOException {
        // Create empty array lists for each artwork type
        ArrayList<Artwork> paintings = new ArrayList<Artwork>();
        ArrayList<Artwork> sculptures = new ArrayList<Artwork>();

        // TEST data, not used in main program,
        // paintings.add( new Artwork("100", "Andres", 2012, "Boston") );

        // Display user main menu
        String menu = "\nChoose an option:\n\n" +
                      "1. Add an artwork\n" +
                      "2. Remove an artwork\n" +
                      "3. Update artwork location\n" +
                      "4. Display artwork information\n" +
                      "5. Display all paintings\n" +
                      "6. Display all sculptures\n" +
                      "7. Exit\n\n";
        Scanner in = new Scanner(System.in); // user input response from main menu
        Category categoryObj; // category class object to hold category information
        String id, type; // variable to hold id passed by user
        Artwork artwork; // artwork object holding artwork object found
        int answer = 0; // keep track of answer value to keep printing main menu

        // Keep printing menu if user provides wrong option as input or until answer == 3
        while (answer != 7) {
            System.out.print(menu); // print main menu
            answer = in.nextInt(); // get user input
            /** Conditional logic for option selected */
            if (answer == 1 || answer == 2 || answer == 3 || answer == 4) {
                id = askID(); // ask user for artwork ID
                categoryObj = getCategory(paintings, sculptures); // get category information
                artwork = searchArtworks(id, categoryObj); // call method to search all artworks
                if (answer == 1) {  // Add artwork
                    // If artwork was not found by searchArtworks(), create new Artwork object
                    if (artwork == null) {
                        // Create new Artwork object
                        newArtWork(categoryObj);
                    } else {
                        // Display error message when tryign to add artwork that is already present
                        System.out.println("\nERROR: Artwork with ID '" + id + "' is already in database:\n");
                        System.out.println(artwork + "\n------------\n\n");
                    }
                } else if (answer == 2) {   // Remove artwork
                    // If artwork was found by searchArtworks(), remove it
                    if (artwork != null) { removeArtwork(artwork, categoryObj); }
                } else if (answer == 3) {   // Update artwork location
                    type =  categoryObj.getType();
                    // If artwork was found by searchArtworks(), ask user for new location
                    if (artwork != null) {
                        System.out.print("Enter new location: "); // ask user for new location
                        String newLocation = in.next(); // get user input
                        artwork.setLocation(newLocation); // set new location entered by user
                        System.out.println("\nSuccessfully updated artwork id '" + id + "' in " + type + ":\n------------\n" + artwork);
                    }
                } else if (answer == 4) {   // Display artwork information
                    ArrayList<Artwork> categoryList = categoryObj.getList(); // get category ArrayList
                    int index = categoryList.indexOf(artwork); // get index in ArrayList for specific Artwork object
                    // If artwork was found by searchArtworks(), display artwork information
                    if (artwork != null) {
                        System.out.println("Artwork found for id '" + id + "':");
                        System.out.println( "\n" + categoryList.get(index) );
                    }
                }
            } else if (answer == 5) {   // Display all paintings
                System.out.println("\nInformation of all paintings:\n---------");
                for (Artwork painting : paintings ) {
                    System.out.println("\n" + painting);
                }
            } else if (answer == 6) {   // Display all sculptures
                System.out.println("\nInformation of all sculptures:\n---------");
                for (Artwork sculpture : sculptures ) {
                    System.out.println("\n" + sculpture);
                }
            }
        }
        System.out.println("Goodbye!"); // print exit message
    }

    /**
    * This class is used to contain category information as an object.
    * It containst the artwork type String entered by the user and
    * the corresponding ArrayList<Artwork> list object.
    */
    public static class Category {
        private String type;
        private ArrayList<Artwork> categoryList;

        public Category(String type, ArrayList<Artwork> categoryList) {
            // Set category string type (painting/sculpture)
            this.type = type;
            // Set ArrayList<Artwork>
            this.categoryList = categoryList;
        }

        public String getType() { return type; }
        public ArrayList<Artwork> getList() { return categoryList; }
    }

    /**
    * This method is used to ask user for an artwork ID.
    * The purpose of this method is to ask the user to an artwork
    * 'id' and return the ID entered.
    * Precondition: Answer to menu item must equal 1-4
    * @param NULL
    * @return returns a string for an artwork ID
    * Postcondition: When this method finishes it returns the ID
    * entered by the user
    */
    public static String askID() {
        // Create variables
        Scanner inID = new Scanner(System.in);
        String id = null;

        // Ask user for ID and set variable from response input
        System.out.print("\nEnter ID of an artwork: ");
        id = inID.next(); // get user input

        return id; // return id to caller
    }

    /**
    * This method is used to ask user for an artwork category (painting or sculpture).
    * The purpose of this method is to ask the user for an artwork type and
    * return a Category class object containing all the information.
    * Precondition: Answer to menu item must equal 1-4
    * @param ArrayList<Artwork>, array list objects of paintings and sculptures Artwork objects
    * @return returns a Category class object with the appropriate artwork type string
    * and ArrayList of artwork objects (paintings or sculptures).
    * Postcondition: When this method finishes it returns the artwork type string and
    * ArrayList<Artwork> as a Category class object corresponding to the user's input of
    * artwork type (painting or sculpture).
    */
    public static Category getCategory(ArrayList<Artwork> paintings, ArrayList<Artwork> sculptures) {
        // Create variables
        Scanner in = new Scanner(System.in);
        String type = null;

        // Ask user for ID and set variable from response input
        System.out.print("Enter artwork type (painting or sculpture): ");
        type = in.next(); // get user input

        // Return type and ArrayList data structure as Category class
        // according to user's selection
        if ( type.equals("painting") ) {
            Category category = new Category(type, paintings);
            return category;
        } else if ( type.equals("sculpture") ) {
            Category category = new Category(type, sculptures);
            return new Category(type, sculptures);
        } else {
            System.out.println("Selected wrong type!");
        }
        return null;
    }

    /**
    * This method is used to search Artwork objects in artworks array.
    * The purpose of this method is to search for artworks with give
    * 'id' passed by user and handle logic with respect to artwork type.
    * Precondition: Answer to menu item must equal 1-4
    * @param String id and Category class object
    * @return Artwork object or null
    * Postcondition: When this method finishes it returns the Artwork
    * object found in the appropriate ArrayList (paintings/sculptures),
    * or if absent prints message and returns null.
    */
    public static Artwork searchArtworks(String id, Category categoryObj) {
        String type = categoryObj.getType(); // get artwork type entered by user (painting/sculpture)
        ArrayList<Artwork> categoryList = categoryObj.getList(); // get category ArrayList
        // Search all artwork objects (records) for 'id' in artworks 'category'
        for (Artwork artwork : categoryList) {
            // If artwork with 'id' is present in category, print error message
            // and return result string "present"
            if (artwork.getID().equals(id)) { return artwork; }
        }
        // Instances were no artwork with 'id' matching response input is present
        System.out.println("\nNo artworks found with id '" + id + "' in " + type + " category.");
        // Return null to caller and handle logic to reprint menu in main method
        // Reflects artwork not found
        return null;
    }

    /**
    * This method is used ask the user for the appropriate artwork
    * attributes to create a new object from these answers. It creates a new
    * Artwork object that is added to the appropriate Arraylist<Artwork>.
    * @param Category class object, ArrayList<Artwork>, for type of artwork
    * ArrayList (painting or sculpture).
    * Postcondition: When this method finishes it creates a new Artwork object,
    * adds it to the corresponding ArrayList of Artwork objects, and displays message after
    * successful operation.
    */
    public static void newArtWork(Category categoryObj) {
        String type = categoryObj.getType(); // get artwork type entered by user (painting/sculpture)
        ArrayList<Artwork> categoryList = categoryObj.getList(); // get category ArrayList

        // Variables to ask user
        String id = null;
        String artist = null;
        int year = 0;
        String location = null;
        String paintType = null;
        String material = null;

        // Ask user for artwork attributes and set variable from response input
        Scanner in = new Scanner(System.in);
        System.out.print("\nEnter artwork ID: ");
        id = in.next(); // get user input
        System.out.print("\nEnter artwork artist: ");
        artist = in.next(); // get user input
        System.out.print("\nEnter artwork year: ");
        year = in.nextInt(); // get user input
        System.out.print("\nEnter artwork location: ");
        location = in.next(); // get user input
        System.out.print("\nEnter artwork material: ");
        material = in.next(); // get user input

        if (type.equals("painting")) {
            // Ask user for paint type, only in paintings
            System.out.print("\nEnter artwork paintType: ");
            paintType = in.next(); // get user input
            // Add new Painting object to category ArraList<Artwork> paintings
            categoryList.add( new Painting(id, artist, year, location, paintType, material) );
        } else if (type.equals("sculpture")) {
            double height = 0;
            double weight = 0;
            // Ask user for height and weight, only in sculptures
            System.out.print("\nEnter artwork height: ");
            height = in.nextInt(); // get user input
            System.out.print("\nEnter artwork weight: ");
            weight = in.nextInt(); // get user input
            // Add new Sculpture object to category ArraList<Artwork> sculpture
            categoryList.add( new Sculpture(id, artist, year, location, material, height, weight) );
        }
        // Print success message
        System.out.println("\nSuccessfully added new " + type + " to list!\n");
    }

    /**
    * This method is used to remove the Artwork object from the corresponding
    * ArrayList when option 2 ("Remove an artwork") is selected.
    * @param Artwork and Category: Artwork object and Category class object
    * Postcondition: When this method finishes it removes the passed Artwork object,
    * from the corresponding ArrayList of Artwork objects, and displays message after
    * successful operation.
    */
    public static void removeArtwork(Artwork artwork, Category categoryObj) {
        String type = categoryObj.getType(); // get artwork type entered by user (painting/sculpture)
        ArrayList<Artwork> categoryList = categoryObj.getList(); // get category ArrayList
        String id = artwork.getID(); // get id of artwork for message

        // Remove specified artwork with id = 'id' from ArrayList
        categoryList.remove(artwork);
        // Print success message
        System.out.println("\nArtwork with id '" + id + "' in " + type + " successfully removed!");
    }
}
