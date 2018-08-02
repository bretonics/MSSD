/**
* Person.java
* This is a Person class for the Class Artwork
* Precondition: Birth year must be positive
* @author  Andres Breton
* @version 1.0
* Postcondition: Person object created
*/
class Person {

    // Instance Variables
    public String name;
    public int birthYear;

    // Default class constructor
    public Person (String name, int birthYear) {
        if (birthYear < 0) {
            System.out.println("Birth year ''" + birthYear + "'' is negative. Can not proceed!");
            System.exit(0);
        }
        this.name = name;
        this.birthYear = birthYear;
    }

    /** Class Methods */

    // Accessor Methods
    public String getName() { return name; }
    public int getBirthYear() { return birthYear; }

    // Update Methods
    public void setName(String name) { this.name = name; }
    public void setBirthYear(int birthYear) { this.birthYear = birthYear; }

    // Object string representation
    public String toString() {
        String person = "name: " + name + "\nbirth year: " + birthYear;
        return person;
    }
}
