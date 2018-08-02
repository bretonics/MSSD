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
    public Painting (String id, String artist, int year, String location, String paintType, String material) {
        // Invoke the constructor of the superclass, Artwork()
        super(id, artist, year, location);
        // Check and set instance variables
        this.paintType = paintType;
        this.material = material;
    }

    public String toString() {
        String painting = "id: " + this.id + "\nartwork type: painting\nartist: " + this.artist + "\nyear: " + this.year + "\nlocation: " + this.location + "\npaint type: " + this.paintType + "\nmaterial: " + this.material;
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
