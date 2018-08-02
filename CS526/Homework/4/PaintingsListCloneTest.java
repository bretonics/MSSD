import java.io.*;
import java.util.*;

public class PaintingsListCloneTest {

    /**
    * This main method creates an independent copy of a paintings list, prompts
    * user for an id and a new birth year, and changes the corresponding
    * painting object's Person object's (artist) birth year. It finishes by
    * printing both the original paintings list with the new birth year changes
    * and the independent copy created without those changes.
    * @param args Unused.
    * @return Void.
    * @exception IOException On input error.
    * @see IOException
    * Output: Prints all paintings in original, modified SinglyLinkedList and
    * it's independent, unmodified SinglyLinkedList
    * Postcondition: An independent SinglyLinkedList<Painting> paintings list copy
    */
    public static void main(String[] args) throws IOException {
        // Create singly linked list of paintings artwork type
        SinglyLinkedList<Painting> paintingsList = new SinglyLinkedList<Painting>();

        // Create Person objects to add to Painting objects
        Person artist0 = new Person("Andres", 2000);
        Person artist1 = new Person("Adrian", 1976);
        Person artist2 = new Person("Thomas", 1999);
        Person artist3 = new Person("Carol", 1955);
        Person artist4 = new Person("Noel", 1989);
        Person artist5 = new Person("Cami", 2001);

        // Add painting objects to SinglyLinkedList
        paintingsList.addLast( new Painting("100", artist0, 2012, "Colombia", "tempera", "wood") );
        paintingsList.addLast( new Painting("101", artist1, 2013, "Argentina", "acrylic", "cardboard") );
        paintingsList.addLast( new Painting("102", artist2, 2014, "Boston", "oil", "canvas") );
        paintingsList.addLast( new Painting("103", artist3, 2015, "Boston", "watercolor", "paper") );
        paintingsList.addLast( new Painting("104", artist4, 2016, "Miami", "tempera", "canvas") );
        paintingsList.addLast( new Painting("105", artist5, 2018, "Philadelphia", "oil", "canvas") );

        // Print all painting objects (records) in SinglyLinkedList<Painting>
        System.out.println("This is ORIGINAL paintings list:\n");
        System.out.println(paintingsList);

        // Create SinglyLinkedList<Painting> copy
        ArtworkListManagement artworkManagement = new ArtworkListManagement();
        SinglyLinkedList<Painting> paintingsListClone = artworkManagement.copyPaintingsList(paintingsList);
        // Print all Painting objects in SinglyLinkedList<Painting> copy
        System.out.println("This is CLONE paintings list:\n");
        System.out.println(paintingsListClone);

        // Prompt user for new birth year and set new one in paintingsList
        newBirthYear(paintingsList);

        // Print both Paintings lists after update
        System.out.println("This is ORIGINAL paintings list after update:\n");
        System.out.println(paintingsList);
        System.out.println("This is CLONE paintings list after update:\n");
        System.out.println(paintingsListClone);
    } //----------- end of main method -----------

    /**
    * This method prompts the user for an artwork id and new birth year, then changes the
    * corresponding id's artist's birth year.
    * @param None
    * return Void
    * Postcondition: Changed birth year for corresponding id's artist
    */
    public static void newBirthYear(SinglyLinkedList<Painting> paintingsList) {
        // Ask user for ID and new birth year
        Scanner in = new Scanner(System.in);
        System.out.print("Enter artwork ID: "); // ask user for ID
        String id = in.next(); // get user input
        System.out.print("Enter new birth year for " + id + ": "); // ask user for new birth year
        int birthYear = in.nextInt(); // get user input

        // Search all painting objects (records) for 'id'
        SinglyLinkedList.Node<Painting> walk = paintingsList.head; // get head element in SinglyLinkedList
        while (walk != null) { // lets begin iterating through each element
            Painting painting = walk.getElement(); // get painting object in element of SinglyLinkedList
             // If 'id' of painting in element matches, set new birth year
            if (painting.getID().equals(id))  {
                Person artist = painting.getArtist(); // get Person object for artist
                String name = artist.getName();
                artist.setBirthYear(birthYear); // set new birth year for corresponding Person object (artist)
                System.out.println("\nSuccessfully updated artwork id '" + id + ":\n------------\n" + painting + "\n------------\n\n");
                return; // return to caller once set and done
            }
            walk = walk.getNext(); // get next element (painting object)
        }
        // Instances were no artwork with 'id' matching response input is present
        // Return null to caller and handle logic to reprint menu in main method
        // Reflects artwork not found
        System.out.println("\nNo artworks found with id '" + id + "' in paintings");
    }
}
