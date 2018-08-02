/**
* Sculpture.java
* This is a subclass of the Class Artwork
* @author  Andres Breton
* @version 1.0
*/
class Sculpture extends Artwork {

    // Instance Variables
    public String material;
    public double height;
    public double weight;

    // Default class constructor
    public Sculpture (String id, Person artist, int year, String location, String material, double height, double weight) {
        // Invoke the constructor of the superclass, Artwork()
        super(id, artist, year, location);
        // Check and set instance variables
        if (height < 0 || weight < 0) {
            System.out.println("Height and weight must be positive. Can not proceed.");
            System.exit(0);
        }
        this.material = material;
        this.height = height;
        this.weight = weight;
    }

    public String toString() {
        String sculpture = "id: " + id + "\nartwork type: sculpture\nartist: " + artist + "\nyear: "+ year + "\nlocation: " + location + "\nmaterial: " + material + "\nheight: " + height + "\nweight: " + weight;
        return sculpture;
    }

    /** Class Methods */

    // Accessor Methods
    public String getMaterial() { return material; }
    public double getHeight() { return height; }
    public double getWeight() { return weight; }

    // Update Methods
    public void setMaterial(String material) { this.material = material;}
    public void setHeight(double height) { this.height = height;}
    public void setWeight(double weight) { this.weight = weight;}

}
