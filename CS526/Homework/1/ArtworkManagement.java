import java.io.*;
import java.util.Scanner;
import java.util.Arrays;


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
        // Get file content
        Scanner fileInput = new Scanner(new File("artwork_info.txt"));
        // Handle first line of file and create array to hold objects
        int size = Integer.parseInt(fileInput.nextLine()); // stores first line of file as number of artworks to set size of array
        Artwork[] artworks = new Artwork[size];

        // Traverse through file content, to read and store information in array of Artwork objects
        int i=0;
        while (fileInput.hasNext()) {
            String[] line = fileInput.nextLine().split(",\\s+"); // get line in file as array split by delim
            // Set values or recrod to variables
            String id = line[0];
            String artist = line[1];
            int year = Integer.parseInt(line[2]);
            String location = line[3];
            artworks[i] = new Artwork(id, artist, year, location); // create new object in array with values obtained
            i++; // increment to store new record in artworks array
        }

        // Display user main menu
        String menu = "Choose an option:\n\n" +
                            "1. Artwork information\n" +
                            "2. Update location\n" +
                            "3. Exit\n\n";
        Scanner in = new Scanner(System.in); // user input response from main menu
        int answer = 0; // keep track of answer value to keep printing main menu

        // Keep printing menu if user provides wrong option as input or until answer == 3
        while (answer != 3) {
            System.out.print(menu); // print main menu
            answer = in.nextInt(); // get user input

            // Conditional logic to option selected
            if (answer == 1) {
                Artwork artwork = searchArtworks(artworks); // call method to search alll artworks
                // If 'id' was found by searchArtworks() method, print artwork object
                if (artwork != null) {
                    System.out.println("\nFound\n------------\n" + artwork + "------------\n");
                }

            } else if (answer == 2) {
                Artwork artwork = searchArtworks(artworks); // call method to search alll artworks
                // If 'id' was found by searchArtworks() method, ask user for new location
                if (artwork != null) {
                    System.out.print("Enter new location: "); // ask user for new location
                    String newLocation = in.next(); // get user input
                    artwork.setLocation(newLocation); // set new location entered by user
                    System.out.println("\nUpdated:\n------------\n" + artwork);
                }
            }
        }
        System.out.println("Goodbye!"); // print exit message
    }

    /**
    * This method is used to search Artwork objects in artworks array.
    * The purpose of this method is to search for artworks with give
    * 'id' passed by user and handle logic.
    * Precondition: Answer to menu item must equal 1 or 2
    * @param Artwork[], artworks array of Artwork objects
    * @return Artwork object or null
    * Postcondition: When this method finishes it returns the Artwork object
    * that matched the given id, or prints error message that id was not
    * present in records obtained from file in main method.
    */
    public static Artwork searchArtworks(Artwork[] artworks) {
        // Create variables
        Scanner inID = new Scanner(System.in);
        String result = null;
        String id = null;

        // Ask user for ID and set variable from response input
        System.out.print("\nEnter ID of an artwork: ");
        id = inID.next(); // get user input
        // Search all artwork objects (records) for 'id' response input
        for (Artwork artwork : artworks) {
            // If artwork with 'id' is present, return artwork object
            if (artwork.getID().equals(id)) {
                result = "found"; // sets result to "found" to skip logic for no 'id' being matched
                return artwork;
            }
        }
        // Handle instances were no artwork with 'id' matching response input is present
        if (result != "found") {
            System.out.println("\nSorry! ID '" + id + "' not found.\n\n"); // print error message
        }
        // Return null to caller, reflects no artwork found
        // and handle logic to reprint menu in main method
        return null;
    }
}
