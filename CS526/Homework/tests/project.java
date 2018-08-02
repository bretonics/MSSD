/**
* project.java
* This class implements two heurist algorithms to determine the shortest path in a graph.
* It uses a user's prompted input as the starting node and "Z" node as its destination,
* along with 2 files: an adjency matrix representation of the graph to traverse and
* direct distance values (geographically straight line) of each node to node "Z".
* It prints results for each algorithm to stdout with overal path taken in graph as
* well as the shortest path from starting node to node "Z".
*
* Algorithm heuristics --
* Algorithm 1: next node is one with smallest direct distance (dd) between all adjacent nodes of current node
* Algorithm 2: next node is one with smallest (edge weight + dd) value between all adjacent nodes of current node
* @author  Andrés Bretón
* @version 1.0
*/

import java.io.*;
import java.util.*;
import java.util.Scanner;


public class project {

    /**
    * @param args Unused.
    * @return Void.
    * @exception IOException On input error.
    * @see IOException
    * Output: Prints algorithm results of overall path and shortest path taken in graph
    * to stdout according to user input start node.
    */
    public static void main(String[] args) throws IOException {

    // Input files
    Scanner graphFile = new Scanner(new File("graph_input.txt")); // graph file object
    Scanner distanceFile = new Scanner(new File("direct_distance.txt")); // distance file object

    // Data structures for adjacency graph and direct distance information
    String[][] graph = parseGraph(graphFile); // Parse graph file
    Map<String, Integer> distances = parseDistance(distanceFile); // Parse direct distance file

    ArrayList<String> vertexNames = getVertices(graph); // get vertex names
    String startNode = promptUser(vertexNames); // prompt user for start node

    shortestPath(graph, distances, 1, startNode); // find shortest paths with algorithm 1
    shortestPath(graph, distances, 2, startNode); // find shortest paths with algorithm 2

}  // --------------------------- END OF MAIN METHOD ---------------------------

    /**
    * Method used to parse the graph input file.
    * @param Graph file object.
    * @return String[][] adjacency matrix of graph in string format.
    */
    public static String[][] parseGraph(Scanner file) {
        String[][] matrix = new String[26][26];
        int i = 0; // start of edges in each row

        // Process graph file
        while (file.hasNext()) { // loop every line
            String line = file.nextLine(); // Get line as string
            String[] values = line.split("\\s+"); // Split line by space(s)
            int nNodes = values.length;
            // Get each vertex's edge weight -
            // Skip first column (j = 1), vertex names, which have already
            // been captured in 1st row
            for (int j = 1; j < nNodes; j++) {
                String edge = values[j];
                matrix[i][j-1] = edge; // need -1 because we want to store starting at index = 0
            }
            i++;
        }
        return matrix;
    }

    /**
    * Method used to parse the vertices direct distances
    * @param Scanner distance file object.
    * @return Map<String, Integer> HashMap containing each vertex name and its direct distance.
    */
    public static Map<String, Integer> parseDistance(Scanner file) {
        // HashMap to hold vertex name and its direct distance
        Map<String, Integer> distances = new HashMap<String, Integer>();
        // Process distance file
        while (file.hasNext()) { // loop every line
            String line = file.nextLine(); // Get line as string
            String[] values = line.split("\\s+"); // Split line by space(s)
            // Add key (vertex name), value (direct distance) pair to HashMap
            distances.put(values[0], Integer.parseInt(values[1]));
        }
        return distances;
    }

    /**
    * Method used to find the shortest path from the start (input) node to node Z
    * @param String[][] adjacency matrix graph representation
    * @param Map<String, Integer> HashMap interface containing each vertex
    * @param Integer of heurist algorithm to use to find shortest path
    * @param String starting node
    * @return Void
    * Output: Prints shortest distance information for algorithm passed
    */
    public static void shortestPath(String[][] graph, Map<String, Integer> distances, Integer algorithm, String startNode) {
        ArrayList<String> vertexNames = getVertices(graph); // get vertex names
        int numVertices = vertexNames.size(); // number of vertices
        Map<String, Integer> adjacentNodes = new LinkedHashMap<String, Integer>(); // adjacent nodes of currentNode
        Map<String, Integer> shortestPath = new LinkedHashMap<String, Integer>(); // to store nodes of shortes path
        ArrayList<String> path = new ArrayList<String>(); // to store nodes of path taken
        String nextNode = ""; // next node selected for path
        String previousNode = ""; // previous node in path
        String deadEnd = ""; // deadEnd node in path
        int sum = 0; // sum of edges in shortest path

        nextNode = startNode; // next node will initially be starting node from user
        shortestPath.put(startNode, 0); // add start node to shortest path of nodes taken
        path.add(startNode); // add start node to overall path of nodes taken

        // Don't stop until last node is "Z"
        while (!nextNode.equals("Z")) {
            // Loop through adjacency matrix (n is index [row] of currenNode)
            for (int n = 0; n < numVertices; n++) {
                String currentNode = vertexNames.get(n); // current node in loop

                // Look for nextNode
                if (currentNode.equals(nextNode)) {
                    adjacentNodes.clear(); // clear HashMap as now at new currentNode

                    // Loop through every edge in currentNode to store adjacent nodes in
                    // adjacentNodes Map<String, Integer>:
                    // Key == vertex name string
                    // Value == edge distance int
                    for (int e = 0; e < numVertices; e++) {
                        String vertex = vertexNames.get(e); // current vertex name
                        int edge = Integer.parseInt(graph[n+1][e]); // n + 1 required since index start at 0 column == vertex names
                        // Store only connecting edges
                        if (edge != 0) {
                            // Make sure node to add is not one previously stored as being a "dead end"
                            // Prevents from adding a node that was determined to have only 1 connecting
                            // node which was its previous one, i.e.) G -> H -> T -> H, backtrack to H (previous node)
                            // without going back into dead end not T (node previously tested)
                            if (!vertex.equals(deadEnd)) {
                                adjacentNodes.put(vertex, edge); // store adjacent nodes
                            }
                        }
                    }
                    int numAdjacent = adjacentNodes.size();
                    Iterator<Map.Entry<String, Integer>> adjacent = adjacentNodes.entrySet().iterator();
                    String vertex = adjacent.next().getKey(); // first adjacent node

                    // If node has only 1 edge (meaning its only other node is the previous one visited),
                    // we need to go back to previous node and select other adjacent node -- mark dead end reached.
                    if (previousNode.equals(vertex) && numAdjacent == 1) {
                            path.add(vertex); // add probed vertex to overall path
                            shortestPath.remove(currentNode); // remove previously added node to shortest path (no longer valid)
                            nextNode = previousNode; // reset node back to previous node (backtrack)
                            deadEnd = currentNode; // set node that was "dead end"
                    } else {
                        // DETERMINE NEXT NODE IN PATH -- Algorithm dependent
                        previousNode = path.get(path.size()-1); // store previous node's vertex name
                        nextNode = smallest(adjacentNodes, distances, algorithm); // get next node of shortest path
                        shortestPath.put(nextNode, adjacentNodes.get(nextNode)); // add selected node to shortest path
                        path.add(nextNode); // add selected node to overall path
                    }
                    if (nextNode.equals("Z")) { break; } // exit once at node Z
                } // ----------------------- END IF LOOP -----------------------
            } // ---------------------- END OUTER FOR LOOP ----------------------
        } // -------------------------- END WHILE LOOP --------------------------

        // Algorithm Results
        System.out.println("\nAlgorithm " + algorithm + ":\n");

        // Print overall path with sequence of all nodes
        if (!path.isEmpty()) {
            System.out.print("\tSequence of all nodes: " + path.get(0)); // print first node
        }
        for (int j = 1; j < path.size(); j++) {
            System.out.print(" -> " + path.get(j)); // print subsequent nodes
        }
        System.out.println();

        // Print shortest path
        Iterator<Map.Entry<String, Integer>> paths = shortestPath.entrySet().iterator(); // create iterator
        String firstNode = paths.next().getKey(); // get first node
        System.out.print("\tShortest path: " + firstNode);
        while (paths.hasNext()) { // loop through subsequent nodes
            Map.Entry<String, Integer> currentNode = paths.next();
            System.out.print(" -> " + currentNode.getKey());
            sum += currentNode.getValue();
        }
        System.out.println("\n\tShortest path length: " + sum + "\n");
    } // ---------------------- END OF SHORTESTPATH METHOD ----------------------

    /**
    * Method used to get vertex names in graph.
    * @param String[][] adjacency matrix graph representation.
    * @return ArrayList<String> of vertex names.
    */
    public static ArrayList<String> getVertices(String[][] graph) {
        ArrayList<String> vertices = new ArrayList<String>();
        int nV = graph[0].length;
        // Loop through first row containing vertex names
        for (int n = 0; n < nV; n++) {
            String vertex = graph[0][n];
            if(vertex == null) { continue; } // handle null cases
            vertices.add(vertex.toUpperCase()); // store vertex, upcase for comparison consistency
        }
        return vertices;
    }

    /**
    * Method used to prompt user for a start node (vertex).
    * @param ArrayList<String> vertices variable containing vertex names to search.
    * @return String of start node entered by user.
    */
    public static String promptUser(ArrayList<String> vertices) {
        Scanner in = new Scanner(System.in);
        String vertex = "";
        System.out.println("\nWill continue to ask for start node if node entered is not valid.");
        // Keep asking user for start node if input node is not valid
        while (!vertices.contains(vertex)) {
            System.out.print("\nEnter a start node: ");
            vertex = in.next().toUpperCase();  // upcase for comparison consistency
        }
        return vertex;
    }

    /**
    * Method used to get the next node to traverse dependent on which heuristic algorithm is being used.
    * Algorithm 1: node with smallest direct distance (dd) between all adjacent nodes of current node.
    * Algorithm 2: node with smallest (edge weight + dd) value between all adjacent nodes of current node.
    * @param Map<String, Integer> of adjacent nodes.
    * @param Map<String, Integer> of direct distances.
    * @param Integer of heuristic algorithm to obtain next node.
    * @return String node with smallest dd value from all adjacent nodes.
    */
    public static String smallest(Map<String, Integer> adjacentNodes, Map<String, Integer> distances, Integer algorithm) {
        Iterator<Map.Entry<String, Integer>> adjacent = adjacentNodes.entrySet().iterator(); // create iterator
        Map.Entry<String, Integer> entry = adjacent.next(); // get next key/value pair

        String node = entry.getKey(); // initialize first vertex name
        Integer smallestW = entry.getValue(); // initialize smallest (first) edge weight
        Integer smallestDD = distances.get(node); // initialize smallest (first) direct distance
        Integer smallest = smallestW + smallestDD; // initialize smallest (edge weight + direct distance)

        // Loop through every other adjacent node and check its edge value and dd
        while (adjacent.hasNext()) {
            entry = adjacent.next();
            Integer w = entry.getValue(); // get weight of current adjacent node
            Integer dd = distances.get(entry.getKey()); // get dd value of current adjacent node

            // Determine next node depending on which heuristic algorithm being used
            if (algorithm == 1) {
                // Compare direct distance of all adjacent nodes of currentNode, get smallest direct distance node
                if (dd < smallestDD) {
                    node = entry.getKey();
                    smallestDD = dd; // set smallest to new edge value
                }
            } else { // algorithm 2
                if (w + dd < smallest) {
                    node = entry.getKey();
                    smallest = w + dd; // set smallest (edge weight + direct distance)
                }
            }
        }
        return node;
    }
}
