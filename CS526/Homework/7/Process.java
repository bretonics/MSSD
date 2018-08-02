/**
* Process.java
* This class implements a Process object with instance variables process id, priority, duration, and arrival time
* @author  Andres Breton
* @version 1.0
*/
class Process implements Comparable<Process> {

    // Instance variables
    public int id;
    public Integer priority;
    public int duration;
    public int arrival;
    public int wait = 0; // keep track of time units passed in simulation

    // Default class constructor
    public Process (int id, Integer priority, int duration, int arrival) {
        this.id = id;
        this.priority = priority;
        this.duration = duration;
        this.arrival = arrival;
    }

    /** Class Methods */

    // Comparable method to sort Object by arrival time property
    public int compareTo(Process comparison) {
        int compareArrival = ((Process) comparison).getArrival();
        // Ascending order
        return this.arrival - compareArrival;
    }


    // Accessor Methods
    public int getID() { return id; }
    public Integer getPriority() { return priority; }
    public int getDuration() { return duration; }
    public int getArrival() { return arrival; }
    public int getWait() { return wait; }

    // Update Methods
    public void addWait() { this.wait++; }
    public void decrementPriority() { this.priority--; }

}
