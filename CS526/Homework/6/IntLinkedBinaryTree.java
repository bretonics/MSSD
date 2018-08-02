/**
* IntLinkedBinaryTree.java
* This class implements an Integer binary tree that uses a linked structure
* (LinkedBinaryTree) that extends the AbstractBinaryTree class. This is a subclass
* that stores elements of Integer type objects in the tree nodes. It follows the
* following binary search tree property for each internal position p:
*   Elements stored in the left subtree of p are less than p’s element.
*   Elements stored in the right subtree of p are greater than p’s element.
*/

import java.util.*;

public class IntLinkedBinaryTree extends LinkedBinaryTree<Integer>{

    // Default class constructor
    public IntLinkedBinaryTree(Integer e, Position<Node> above, Position<Node> leftChild, Position<Node> rightChild) {
        super();
    }

    // Accessor methods
    public int getHeight(Position<Integer> p) {
        // Node is leaf
        if (p == null) {
            return 0; // no nodes to account for
        } else { // calculate depth of each subtree
            int leftST = getHeight(left(p));
            int rightST = getHeight(right(p));

            // Return largest node (more children)
            if (leftST > rightST) { // left subtree has more children nodes
                // Return left subtree count +1 for current node
                return leftST + 1;
            } else { // right subtree has more children nodes
                // Return right subtree count +1 for current node
                return rightST + 1;
            }
        }
    }


    /**
     * Add a new node with e to the tree rooted at position p
     * @param p The root of the tree to which new node is added
     * @param e The element of the new node
     * @return If a node with e does not exist, a new node with e is added and
     *   reference to the node is returned. If a node with e exists, null is returned.
     */
    public Position<Integer> add(Position<Integer> p, Integer e){
        // Leaf node
        if (p == null) {
            // Return a new node 'e' and make it root
            return addRoot(e);
        }

        // Place holders to help pass nodes around when setting parent/new children
        Position<Integer> x = p;
        Position<Integer> y = x;
        // System.out.println("X is " + x.getElement());
        // System.out.println("p is " + p.getElement());

        // Loop until leaf node reached
        while( x != null ) {
            // System.out.println("left is " + left(x).getElement());
            // Store elements in the left subtree of 'p' when 'e' is less than p’s element
            if( x.getElement() == e ) {
                return null; // same element
            // Set left node of node passed if 'e' element is smaller than current node element
            } else if ( e < x.getElement()) {
                // System.out.println("left X is " + left(x));
                // System.out.println(e + " is < " + x.getElement() + ". Adding to left");
                y = x; // set 'y' placeholder node position as current 'x' node
                x = left(x); // 'x' == 'p', so setting as left node of original Position<Intiger> passed
                // System.out.println("X is now " + x);
                // System.out.println("left X is now " + left(x));
                // System.out.println("rigt X is now " + right(x));
                // System.out.println("left p is now " + left(p));
                // System.out.println("right p is now " + right(p));
            // Set right node of node passed if 'e' element is bigger than current node element
            } else {
                // System.out.println("right X is " + right(x));
                // System.out.println(e + " is > " + x.getElement() + ". Adding to right");
                y = x; // set 'y' placeholder node position as current 'x' node
                // System.out.println("X is " + x);
                x = right(x); // 'x' == 'p', so setting as right node of original Position<Intiger> passed
                // System.out.println("X is now " + x);
                // System.out.println("left X is now " + left(x));
                // System.out.println("right X is now " + right(x));
                // System.out.println("left p is now " + left(p));
                // System.out.println("right p is now " + right(p));
            }
        } // --------- END WHILE LOOP ---------

        // Temporary node holding 'e' element,
        Position<Integer> temp = new Node<Integer>(e, null, null, null);
        // System.out.println("temp is " + temp.getElement());

        // If 'e' element is smaller than current node element, add Position<Integer> node as left node of current node element
        if ( e < y.getElement()) {
            // System.out.println(e + " is < " + y.getElement() + ". Adding to left");
            addLeft(y, temp.getElement()); // add 'e' element as left node of current node 'y' == original 'p' passed
            // System.out.println("Y is now " + y.getElement());
            // System.out.println("left Y is now " + left(y).getElement());
            // System.out.println("rigt Y is now " + right(y));
            // System.out.println("left p is now " + left(p).getElement());
            // System.out.println("right p is now " + right(p));
        // If 'e' element is bigger than current node element, add Position<Integer> node as right node of current node element
        } else {
            // System.out.println(e + " is > " + y.getElement() + ". Adding to right");
            addRight(y, temp.getElement()); // add 'e' element as right node of current node 'y' == original 'p' passed
            // System.out.println("Y is now " + y.getElement());
            // System.out.println("right Y is now " + right(y).getElement());
            // System.out.println("left p is now " + left(p).getElement());
            // System.out.println("right p is now " + right(p).getElement());
        }
        return temp;
    }

    public static void main(String[] args) {

        // Create a new binary tree instance
        IntLinkedBinaryTree t = new IntLinkedBinaryTree(0, null, null, null);

        // add some integers
        t.add(t.root, 100);
        t.add(t.root, 50);
        t.add(t.root, 150);
        t.add(t.root, 70);

        // print on the screen all integers in the tree in increasing order
        // after adding above four integers, the following should be printed
        // 50 70 100 150
        Iterator<Position<Integer>> it = t.inorder().iterator(); // iterable collection of positions of the tree
        while ( it.hasNext() ) { // Traverse tree until leaf node (children == null)
            System.out.print(it.next().getElement() + " "); // print stored element
        }
        System.out.println();
        System.out.println("Height of above tree is " + t.getHeight(t.root()));
        System.out.println("\n");


        // experimentally determine the average height of a binary search tree
        // clear the tree before beginning this experiment
        // repeat 100 times
        int maxIteration = 100;
        int[] heights = new int[maxIteration]; // store heights of 100 generated trees

        for (int i= 0; i < maxIteration; i++){
            // Create variables to keep track of tree data
            int[] numbers = new int[1000]; // array to store 1000 distinct integers
            int number = 0; // initialize number variable to store
            Random rn = new Random(); // instantiate random number object

            // Begin with an empty tree in each iteration
            IntLinkedBinaryTree tree = new IntLinkedBinaryTree(0, null, null, null);

            // Generate 1,000 random integers in the range [0, 999999]
            for(int j = 0; j < 1000; j++) {
                // Make sure the resulting tree has 1000 distinct integers
                Boolean response = true;
                while(response) { // loop until new random number is unique from stored numbers
                    number = rn.nextInt(100000);
                    // Check if duplicate found in all generated numbers
                    for(int x : numbers){
                        // If new random number was already generated/stored, skip and get new random number
                        if(x == number){
                            break;
                        } else {
                            // Set response to false to break out of generating new random numbers
                            response = false;
                            break;
                        }
                    }
                }
                // Store new unique random number in array
                numbers[j] = number;
                // Add each of the 1,000 random integers to the tree, one at a time
                tree.add(tree.root, number);
                // System.out.println("Number stored: " + number);
            }

            // Determine the height of the resulting tree
            int height = tree.getHeight(tree.root());
            heights[i] = height; // store height of generated tree

            // Print on the screen the number of nodes and the the height of the tree
            System.out.println("Nodes in the tree #" + i + ": " + tree.size());
            System.out.println("Height of the tree #" + i + ": " + height + "\n");

        }

        // Calculate and display the average height of the 100 trees
        int sum = 0;
        for (int n : heights) { sum += n; }
        System.out.println("\n");
        System.out.println("Average height of " + maxIteration + " trees: " + sum/maxIteration);
        System.out.println();
    }
}
