
import java.io.*;
import java.util.*;


public class ArtworkListManagement{

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
        // Create empty singly linked lists for each artwork type
        SinglyLinkedList<Artwork> paintings = new SinglyLinkedList<Artwork>();
        SinglyLinkedList<Artwork> sculptures = new SinglyLinkedList<Artwork>();

        // TEST data, not used in main program,
        // Create Person objects to add to Artowkr objects
        Person artist0 = new Person("Andres", 2000);
        Person artist1 = new Person("Adrian", 1976);
        paintings.addFirst( new Artwork("100", artist0, 2012, "Boston") );
        sculptures.addFirst( new Sculpture("100", artist0, 2012, "Boston", "wood", 23, 34) );
        paintings.addLast( new Artwork("101", artist1, 2012, "Raleigh") );
        sculptures.addLast( new Sculpture("101", artist1, 2012, "Raleigh", "wood", 23, 34) );

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
                // Encapsulate category information in object containing category
                // type string (painting/sculpture) and appropriate SinglyLinkedList object
                categoryObj = getCategory(paintings, sculptures);
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
                        System.out.println("\nSuccessfully updated artwork id '" + id + "' in " + type + ":\n------------\n" + artwork + "\n------------\n\n");
                    }
                } else if (answer == 4) {   // Display artwork information
                    // If artwork was found by searchArtworks(), display artwork information
                    if (artwork != null) {
                        System.out.println("Artwork found for id '" + id + "':");
                        System.out.println("---------\n" + artwork + "\n\n---------");
                    }
                }
            } else if (answer == 5) {   // Display all paintings
                System.out.println("\nInformation of all paintings:\n---------\n");
                System.out.println(paintings);
            } else if (answer == 6) {   // Display all sculptures
                System.out.println("\nInformation of all sculptures:\n---------\n");
                System.out.println(sculptures);
            }
        }
        System.out.println("Goodbye!"); // print exit message
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
    * This class is used to contain category information as an object.
    * It containst the artwork type String entered by the user and
    * the corresponding SinglyLinkedList<Artwork> list object.
    */
    public static class Category {
        private String type;
        private SinglyLinkedList<Artwork> categoryList;

        public Category(String type, SinglyLinkedList<Artwork> categoryList) {
            // Set category string type (painting/sculpture)
            this.type = type;
            // Set SinglyLinkedList<Artwork>
            this.categoryList = categoryList;
        }

        public String getType() { return type; }
        public SinglyLinkedList<Artwork> getList() { return categoryList; }
    }

    /**
    * This method is used to ask user for an artwork category (painting or sculpture).
    * The purpose of this method is to ask the user for an artwork type and
    * return a Category class object containing all the information.
    * Precondition: Answer to menu item must equal 1-4
    * @param SinglyLinkedList<Artwork>, singly linked list objects of paintings and sculptures Artwork objects
    * @return returns a Category class object with the appropriate artwork type string
    * and SinglyLinkedList of artwork objects (paintings or sculptures).
    * Postcondition: When this method finishes it returns the artwork type string and
    * SinglyLinkedList<Artwork> as a Category class object corresponding to the user's input of
    * artwork type (painting or sculpture).
    */
    public static Category getCategory(SinglyLinkedList<Artwork> paintings, SinglyLinkedList<Artwork> sculptures) {
        // Create variables
        Scanner in = new Scanner(System.in);
        String type = null;

        // Ask user for ID and set variable from response input
        System.out.print("Enter artwork type (painting or sculpture): ");
        type = in.next(); // get user input

        // Return type and SinglyLinkedList data structure as Category class
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
    * object found in the appropriate SinglyLinkedList (paintings/sculptures),
    * or if absent prints message and returns null.
    */
    public static Artwork searchArtworks(String id, Category categoryObj) {
        String type = categoryObj.getType(); // get artwork type entered by user (painting/sculpture)
        SinglyLinkedList<Artwork> categoryList = categoryObj.getList(); // get category SinglyLinkedList

        // Search all artwork objects (records) for 'id' in artworks 'categoryList'
        SinglyLinkedList.Node<Artwork> walk = categoryList.head; // get head element in SinglyLinkedList
        while (walk != null) { // lets begin iterating through each element
            Artwork artwork = walk.getElement(); // get artwork object in element of SinglyLinkedList
            if (artwork.getID().equals(id)) return artwork; // if 'id' of artwork in element matches, return to caller
            walk = walk.getNext(); // get next element (artwork object)
        }
        // Instances were no artwork with 'id' matching response input is present
        // Return null to caller and handle logic to reprint menu in main method
        // Reflects artwork not found
        System.out.println("\nNo artworks found with id '" + id + "' in " + type + " category.");
        return null;
    }

    /**
    * This method is used ask the user for the appropriate artwork
    * attributes to create a new object from these answers. It creates a new
    * Artwork object that is added to the appropriate SinglyLinkedList<Artwork>.
    * @param Category class object, SinglyLinkedList<Artwork>, for type of artwork
    * SinglyLinkedList (painting or sculpture).
    * Postcondition: When this method finishes it creates a new Artwork object,
    * adds it to the corresponding SinglyLinkedList of Artwork objects, and displays message after
    * successful operation.
    */
    public static void newArtWork(Category categoryObj) {
        String type = categoryObj.getType(); // get artwork type entered by user (painting/sculpture)
        SinglyLinkedList<Artwork> categoryList = categoryObj.getList(); // get category SinglyLinkedList

        // Variables to ask user
        String id = null;
        String name = null;
        int birthYear = 0;
        int year = 0;
        String location = null;
        String paintType = null;
        String material = null;

        // Ask user for artwork attributes and set variable from response input
        Scanner in = new Scanner(System.in);
        System.out.print("\nEnter artwork ID: ");
        id = in.next(); // get user input
        System.out.print("\nEnter artist name: ");
        name = in.next(); // get user input
        System.out.print("\nEnter artist birth year: ");
        birthYear = in.nextInt(); // get user input
        System.out.print("\nEnter artwork year: ");
        year = in.nextInt(); // get user input
        System.out.print("\nEnter artwork location: ");
        location = in.next(); // get user input
        System.out.print("\nEnter artwork material: ");
        material = in.next(); // get user input

        // Create Person object
        Person artist = new Person(name, birthYear);

        if (type.equals("painting")) {
            // Ask user for paint type, only in paintings
            System.out.print("\nEnter artwork paint type: ");
            paintType = in.next(); // get user input
            // Add new Painting object to category ArraList<Artwork> paintings
            categoryList.addLast( new Painting(id, artist, year, location, paintType, material) );
        } else if (type.equals("sculpture")) {
            double height = 0;
            double weight = 0;
            // Ask user for height and weight, only in sculptures
            System.out.print("\nEnter artwork height: ");
            height = in.nextInt(); // get user input
            System.out.print("\nEnter artwork weight: ");
            weight = in.nextInt(); // get user input
            // Add new Sculpture object to category ArraList<Artwork> sculpture
            categoryList.addLast( new Sculpture(id, artist, year, location, material, height, weight) );
        }
        // Print success message
        System.out.println("\nSuccessfully added new " + type + " to list!\n");
    }

    /**
    * This method is used to remove the Artwork object from the corresponding
    * SinglyLinkedList when option 2 ("Remove an artwork") is selected.
    * @param Artwork and Category: Artwork object and Category class object
    * Postcondition: When this method finishes it removes the passed Artwork object,
    * from the corresponding SinglyLinkedList of Artwork objects, and displays message after
    * successful operation.
    */
    public static Artwork removeArtwork(Artwork artwork, Category categoryObj) {
        String type = categoryObj.getType(); // get artwork type entered by user (painting/sculpture)
        SinglyLinkedList<Artwork> categoryList = categoryObj.getList(); // get category SinglyLinkedList
        String id = artwork.getID(); // get id of artwork for message

        // Remove specified artwork with id = 'id' from SinglyLinkedList
        if (categoryList.isEmpty()) return null; // nothing to remove, return to caller
        // Head == current == previous for first element when begining iteration through each element in SinglyLinkedList
        SinglyLinkedList.Node<Artwork> current = categoryList.head; // get head element of SinglyLinkedList, set as current
        SinglyLinkedList.Node<Artwork> prev = current; // set previous element as current
        SinglyLinkedList.Node<Artwork> tail = categoryList.tail; // get tail element in SinglyLinkedList
        int size = categoryList.size(); // get size of SinglyLinkedList to keep track/update when removing

        // Iterate through each element in SinglyLinkedList
        while (current != null) {
            Artwork element = current.getElement(); // get artwork object in current SinglyLinkedList element
            if (element.getID().equals(id)) { // if 'id' of current artwork object is sames as 'id' marked for removal
                System.out.println("Found matching id to remove: " + id);
                SinglyLinkedList.Node<Artwork> next = current.getNext(); // get next node
                // If current node is head node, then head becomes next node
                // This deals with deleting the first node in the LinkedList
                if (current == categoryList.head) categoryList.head = next;
                // set previous node to link to next of current being deleted
                prev.setNext(next);
                size--; // decrease SinglyLinkedList size when element is removed
                if (size == 0) { // special case as list is now empty
                    categoryList.head = null;
                    tail = null;
                }
                // Successful deletion
                System.out.println("\nArtwork with id '" + id + "' in " + type + " successfully removed!");
                return element;
            }
            prev = current;                // set previous as current node
            current = current.getNext();      // will become null if list had only one node
        }
        // Case where item could not be removed
        System.out.println("\nERROR: Artwork with id '" + id + "' in " + type + " NOT removed!");
        return null;
    }

    /**
    * This method creates an independent copy of the paintings list passed.
    * @param SinglyLinkedList
    * @return An independent SinglyLinkedList<Painting> paintings list copy.
    */
    public static SinglyLinkedList<Painting> copyPaintingsList(SinglyLinkedList<Painting> paintingsList) {
        // SinglyLinkedList to return once all elements are copied from original (independent)
        SinglyLinkedList<Painting> listCopy =  new SinglyLinkedList<Painting>();

        SinglyLinkedList.Node<Painting> walk = paintingsList.head; // get head element in SinglyLinkedList
        while (walk != null) { // lets begin iterating through each element
            Painting painting = walk.getElement(); // get artwork object in element of SinglyLinkedList
            // Generate new painting object (copy) from original attributes
            String id = painting.getID();
            Person artist = painting.getArtist(); // get origial Person object
            String name = artist.getName();
            int birthYear = artist.getBirthYear();
            Person newArtist = new Person(name, birthYear); // create new Person object
            int year = painting.getYear();
            String location = painting.getLocation();
            String paintType = painting.getPaintType();
            String material = painting.getMaterial();
            listCopy.addLast( new Painting(id, newArtist, year, location, paintType, material) );
            walk = walk.getNext(); // get next element (artwork object)
        }
        return listCopy; // return independent SinglyLinkedList copy of original
    }
}
