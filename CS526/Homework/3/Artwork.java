/**
* Artwork.java
* This class implements an Artwork object with instance variables:
* id, artist, year, and location. It provides accessor and update
* methods for search and update operations with a default toString
* method that describes the object instance variables.
* @author  Andres Breton
* @version 1.0
*/
class Artwork {

    // Instance variables
    public String id;
    public String artist;
    public int year;
    public String location;

    // Default class constructor
    public Artwork (String id, String artist, int year, String location) {
        if (year < 0) {
            System.out.println("Year ''" + year + "'' is negative. Can not proceed!");
            System.exit(0);
        }
        this.id = id;
        this.artist = artist;
        this.year = year;
        this.location = location;
    }

    /** Class Methods */

    // Accessor Methods
    public String getID() { return id; }

    public String getArtist() { return artist; }

    public int getYear () { return year; }

    public String getLocation () { return location; }

    // Update Methods
    public void setID(String id) { this.id = id; }

    public void setArtist(String artist) { this.artist = artist; }

    public void setYear(Integer year) { this.year = year; }

    public void setLocation(String location) { this.location = location; }

    public String toString() {
        String artwork = "id: " + this.id + "\nartist: " + this.artist + "\nyear: " + this.year + "\nlocation: " + this.location + "\n";
        return artwork;
    }

}
