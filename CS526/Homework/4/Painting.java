/**
* Painting.java
* This is a subclass of the Class Artwork
* @author  Andres Breton
* @version 1.0
*/
class Painting extends Artwork {
    // Instace Variables
    public String paintType;
    public String material;

    // Default class constructor
    public Painting (String id, Person artist, int year, String location, String paintType, String material) {
        // Invoke the constructor of the superclass, Artwork()
        super(id, artist, year, location);
        // Check and set instance variables
        this.paintType = paintType;
        this.material = material;
    }

    public String toString() {
        String painting = "id: " + id + "\ntype: painting\n" + artist + "\nyear: " + year + "\nlocation: " + location + "\npaint type: " + paintType + "\nmaterial: " + material;
        return painting;
    }
    /** Class Methods */

    // Accessor Methods
    public String getPaintType() { return paintType; }
    public String getMaterial() { return material; }

    // Update Methods
    public void setPaintType(String paintType) { this.paintType = paintType; }
    public void setMaterial(String material) { this.material = material; }
}
