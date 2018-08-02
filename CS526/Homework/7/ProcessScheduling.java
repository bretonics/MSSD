import java.io.*;
import java.util.*;
import java.util.Scanner;


public class ProcessScheduling {

    /**
    * This is the main method grabs processes information from a file
    * and store each respective process in a data structure (D) as a Process
    * object containing individual information for each process.
    * It sorts these processes by earliest arrival time and removes each process
    * one at a time into a HeapPriorityQueue (Q) if the arrival time is equal
    * or less than a simulated passage of time in one logical time units per cycle.
    * Precondition: File with processes must have 1 process per line with the
    * following information: process id, priority, arrival time, and duration.
    * @param args file name with processes.
    * @return Void.
    * @exception IOException On input error.
    * @see IOException
    * Output: File output with results.
    * Postcondition: File created with results at process_scheduling_out.txt.
    */
    public static void main(String[] args) throws IOException {
        // Get input file as first command line argument of ask user for input
        Scanner in = new Scanner(System.in); // user input response
        String file = "";
        // Can pass file as first command line argument, or use file from user input at prompt
        // Handle each case
        if (args.length > 1) {
            System.out.println("Please provide 1 input file, java ProcessScheduling inputFile");
        } else if (args.length == 0){
            System.out.print("Please enter input file name: ");
            file = in.next(); // file is string passed by user
        } else {
            file = args[0]; // file name is argument passed
        }
        Scanner inputFile = new Scanner(new File(file)); // file in object

        // Creating a File object for output file
        PrintStream outFile = new PrintStream(new File("process_scheduling_out.txt"));
        // Store current System.out before assigning to outFile stream
        PrintStream console = System.out;
        // Assign outFile to output stream
        System.setOut(outFile);

        // Create empty array list for Processes
        ArrayList<Process> D = new ArrayList<Process>();

        // Create empty array list of wait times
        ArrayList<Integer> waitTimes = new ArrayList<Integer>();

        // Process input file
        System.setOut(console); // reset output stream to stdout (user feedback)
        System.out.println("Processing input file: " + file);
        System.setOut(outFile); // reset output stream to file (store results)
        while (inputFile.hasNext()) { // loop every line
            // Get line as string
            String line = inputFile.nextLine();
            // Split line by space(s) to get each processes' information
            String[] tokens = line.split("\\s+");
            // Create Process object with process information and add it to data structure holding all processes
            int id = Integer.parseInt(tokens[0]);
            Integer priority = Integer.valueOf(tokens[1]);
            int duration = Integer.parseInt(tokens[2]);
            int arrival = Integer.parseInt(tokens[3]);

            // Add new Process object to data structure "processes" (D)
            Process process = new Process(id, priority, duration, arrival);
            D.add(process);
            System.out.println("ID = " + process.getID() + ", priority= " + process.getPriority() + ", duration = " + process.getDuration() + ", arrival time = " + process.getArrival());
        }

        // Sort array of Processes in incrementing order by Process arrival time property
        Collections.sort(D);

        // Start Simulation
        int currentTime = 0; // time unit
        int executionTerm = -1; // variable to set as executing process time termination
        Process runningProcess = new Process(0, 0, 0, 0); // object for current running process
        boolean running = false; // state of process being executed

        // Create Priority queue object- <Priority Integer, Process ref>
        HeapAdaptablePriorityQueue<Integer,Process> Q = new HeapAdaptablePriorityQueue<Integer,Process>();

        // Keep running until all processes have been queued
        while (D.size() != 0) {
            Process process = D.get(0); // current (earliest process) to be queued
            // Check arrival time of first process to be queued
            // Insert to queue if arrival time <= currentTime
            if (process.getArrival() <= currentTime) {
                Q.insert(process.getPriority(), process); // send process to priority queue
                D.remove(0); // remove process that gets queued
            }
            // Remove process from queue and execute
            if (Q.size() != 0 && running == false) {
                // Remove a process with the smallest priority from Q
                runningProcess = Q.removeMin().getValue(); // get the Entry value (Process object)
                running = true; // system is now running a process
                // Set termination time = start time + duration
                executionTerm = runningProcess.getDuration() + currentTime;
                printQueue(runningProcess, currentTime); // print process information
                waitTimes.add(runningProcess.getWait()); // store wait time
            }
            currentTime++; // logical time point increased

            // While process is running, other processes in Q are waiting, so increment wait time
            if (running == true) {
                // If executing process has cycled through its duration time, terminate
                if (currentTime == executionTerm) {
                    running = false; // system is not executing a process now
                    System.out.println("Process " + runningProcess.getID() + " finished at " + currentTime);
                    // Update priorities once process has finished
                    updatePriorities(Q);
                }
                updateWaits(Q);
            }
        } // ---------------------- TIME UNIT ITERATIONS ----------------------

        // Handle orhpaned proceses that were running after D is empty
        while (running == true) {
            // If executing process has cycled through its duration time, terminate
            if (currentTime == executionTerm) {
                running = false; // system is not executing a process now
                System.out.println("Process " + runningProcess.getID() + " finished at " + currentTime);
                // Update priorities once process has finished
                updatePriorities(Q);
            } else {
                updateWaits(Q);
                currentTime++;
            }
        }
        System.out.println("\nD is empty");

        // Process remaining processes in Q
        while (!Q.isEmpty()) {
            // Remove process from queue and execute
            if (Q.size() != 0 && running == false) {
                // Remove a process with the smallest priority from Q
                runningProcess = Q.removeMin().getValue(); // get the Entry value (Process object)
                running = true; // system is now running a process
                // Set termination time = start time + duration
                executionTerm = runningProcess.getDuration() + currentTime;
                printQueue(runningProcess, currentTime); // print process information
                waitTimes.add(runningProcess.getWait()); // store wait time
            }
            currentTime++;
            // While process is running, other processes in Q are waiting, so increment wait time
            if (running == true) {
                // If executing process has cycled through its duration time, terminate
                if (currentTime == executionTerm) {
                    running = false; // system is not executing a process now
                    System.out.println("Process " + runningProcess.getID() + " finished at " + currentTime);
                    // Update priorities once process has finished
                    updatePriorities(Q);
                }
                updateWaits(Q);
            }
        }

        // Handle orhpaned proceses that were running after Q is empty
        while (running == true) {
            // If executing process has cycled through its duration time, terminate
            if (currentTime == executionTerm) {
                running = false; // system is not executing a process now
                System.out.println("Process " + runningProcess.getID() + " finished at " + currentTime);
                // Update priorities once process has finished
                updatePriorities(Q);
            } else {
                updateWaits(Q);
                currentTime++;
            }
        }
        // Metrics
        double sum = 0;
        for( int i : waitTimes) { sum += i; }
        double average = sum/waitTimes.size();
        System.out.println("\nTotal wait time = " + sum);
        System.out.println("Average wait time = " + average);

        // User feedback that we are done
        System.setOut(console); // reset output stream to stdout (user feedback)
        System.out.println("Results saved at: process_scheduling_out.txt");
    } // ------------------------- END OF MAIN METHOD -------------------------

    /**
    * This method prints information of process removed from queue at current time.
    * @param args Process object and int current time.
    * @return Void.
    * Postcondition: Process information has been printed to file
    */
    public static void printQueue(Process p, int t) {
        int wait = t - p.getArrival(); // time process waited in queue
        // To stdout
        System.out.println("\nProcess removed from queue is: id = " + p.getID() + ", at time " + t + ", wait time = " + p.getWait());
        System.out.println("Process id = " + p.getID());
        System.out.println("\tPriority = " + p.getPriority());
        System.out.println("\tArrival = " + p.getArrival());
        System.out.println("\tDuration = " + p.getDuration());
    }

    /**
    * This method updates wait times of processes that have been waiting in
    * HeapPriorityQueue (Q) after every time unit of running time
    * @param args HeapAdaptablePriorityQueue<Integer,Process> Q.
    * @return Void.
    * Postcondition: Processes wait time is incremented by 1 for each time unit
    */
    public static void updateWaits(HeapAdaptablePriorityQueue<Integer,Process> Q) {
        // Temporary structure to hold Processes in Q
        ArrayList<Process> temp = new ArrayList<Process>();
        // Update every Process's wait time
        int Qsize = Q.size();
        for (int i = 1; i <= Qsize; i++) {
            Process p = Q.removeMin().getValue(); // get first Process
            p.addWait(); // increment wait time
            temp.add(p); // add updated process to temporary data structure
        }
        // At this time the Q is empty since all Processes were removed for update
        // Add each updated process back into Q
        for (Process updated : temp) {
            Q.insert(updated.getPriority(), updated); // add each Process back to HeapPriorityQueue (Q)
        }
    }

    /**
    * This method updates priorities of processes that have been waiting
    * longer than max wait time of 10 time units in the HeapPriorityQueue,
    * once the execution of a process has finished. Priority must be betwee
    * between 1 and 10, inclusively.
    * @param args HeapAdaptablePriorityQueue<Integer,Process> Q.
    * @return Void.
    * Postcondition: Processes that has waited more than 10 logical time units
    * has new priority, 1 less than when passed at current time, i.e. priority is
    * decremented by 1.
    */
    public static void updatePriorities(HeapAdaptablePriorityQueue<Integer,Process> Q) {
        // Temporary structure to hold Processes in Q
        ArrayList<Process> temp = new ArrayList<Process>();
        // Update every Process's wait time
        int Qsize = Q.size();
        for (int i = 1; i <= Qsize; i++) {
            Process p = Q.removeMin().getValue(); // get first Process
            // Decrement priority if process has waited longer than 10 time units
            if (p.getWait() >= 10 ) {
                // Do not decrement priority < 1
                if (p.getPriority() != 1) {
                    p.decrementPriority();
                    System.out.println("Priority of process " + p.getID() + " decremented. New priority " + p.getPriority() + "Waited: " + p.getWait()); // print to stdout
                }
            }
            temp.add(p); // add updated process to temporary data structure
        }
        // At this time the Q is empty since all Processes were removed for update, so
        // add each updated process back into Q
        for (Process updated : temp) {
            Q.insert(updated.getPriority(), updated); // add each Process back to HeapPriorityQueue (Q)
        }
    }
}
