/**
* IntLinkedBinaryTree.java
* This class implements an Integer binary tree that uses a linked structure
* (LinkedBinaryTree) that extends the AbstractBinaryTree class. This is a subclass
* that stores elements of Integer type objects in the tree nodes. It follows the
* following binary search tree property for each internal position p:
*   Elements stored in the left subtree of p are less than p’s element.
*   Elements stored in the right subtree of p are greater than p’s element.
*
* This program is menu driven by user input.
*
* @author  Andrés Bretón
* @version 1.0
*/

import java.io.*;
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
    * This is the main method which is driven by user input from a menu.
    * User's menu selection will appropriately execute adding a key to the tree,
    * removing a key from the tree, printing the tree in increasing order, or
    * exit the program.
    * @param args Unused.
    * @return Void.
    * @exception IOException On input error.
    * @see IOException
    * Output: A String main menu and appropriate messages for execution selected
    * Postcondition: IntLinkedBinaryTree will contain the appropriate elements
    * after user's execution of actions performed to tree.
    */
    public static void main(String[] args) {
        // Initialize variables
        int answer = 0; // keep track of answer value to keep printing main menu
        Integer input = 0;

        // Create a new binary tree instance
        IntLinkedBinaryTree t = new IntLinkedBinaryTree(0, null, null, null);

        // TESTING: add some integers
        // t.add(t.root, 20);
        // t.add(t.root, 30);
        // t.add(t.root, 40);
        // t.add(t.root, 60);
        // t.add(t.root, 50);
        // t.add(t.root, 70);
        // t.add(t.root, 80);
        // t.add(t.root, 85);
        // t.add(t.root, 90);
        // t.add(t.root, 100);
        // t.add(t.root, 120);
        // t.add(t.root, 140);
        // t.add(t.root, 150);
        // t.add(t.root, 180);
        // t.add(t.root, 200);

        // User main menu
        String menu = "\nChoose an option:\n\n" +
                      "1. Add a key\n" +
                      "2. Remove a key\n" +
                      "3. Print the tree\n" +
                      "4. Exit\n\n";
        Scanner in = new Scanner(System.in); // user input response from main menu object

        // Keep printing menu if user provides wrong option as input or until answer == 4 (exit)
        while (answer != 4) {
            System.out.print(menu); // print main menu
            answer = in.nextInt(); // get user input

            boolean match; // keep track of element in tree search

            // Logic handling from menu selection
            if (answer == 1) { // add key to tree
                input = prompt(t); // get integer from user input
                match = searchTree(t, input); // search tree for matching integer
                if (match) { // entered integer IS already in tree
                    System.out.println("Key " + input + " already exists in tree!");
                    continue;
                }
                t.add(t.root, input); // add user input to tree
                System.out.print("Key " + input + " added succesfully\n");
            } else if (answer == 2) { // remvove key from tree
                input = prompt(t); // get integer from user input
                match = searchTree(t, input); // search tree for matching integer
                if (!match) { // integer is NOT in tree
                    System.out.println("Key " + input + " does not exists in tree!");
                    continue;
                }
                Integer result = t.delete(t.root ,input); // remove element from tree
                if (result != null) {
                    System.out.print("Key " + input + " deleted succesfully\n"); // succesfully removed
                }
            } else if (answer == 3) {
                // Display all keys in tree in increasing order
                printTree(t);
            }
        }
        System.out.println("\nGoodbye!\n"); // print exit message
    } // ----------------------------- END OF MAIN -----------------------------

    /**
    * Methods used to ask user for an integer.
    * @param IntLinkedBinaryTree tree.
    * @return Integer user entered.
    */
    public static Integer prompt(IntLinkedBinaryTree t) {
        // Create input object, ask user for integer, and return
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter an integer: ");
        return input.nextInt();
    }

    /**
    * This method searches tree for matching integer passed and returns boolean
    * accordingly.
    * @param IntLinkedBinaryTree tree.
    * @param Integer value of element to match
    * @return boolean
    */
    public static boolean searchTree(IntLinkedBinaryTree t, Integer i) {
        // Iterable collection of positions of the tree
        Iterator<Position<Integer>> it = t.inorder().iterator();
        // Traverse tree until element is matched or last element (children == null)
        while ( it.hasNext() ) {
            Integer test = it.next().getElement();
            if( test.equals(i) ) {
                return true; // matched stored element
            }
        }
        // Otherwise no match found for element
        return false;
    }

    /**
    * Method used to print all integers in passed tree in increasing order.
    * @param IntLinkedBinaryTree tree.
    * Output: Prints tree in increasing order to stdout.
    */
    public static void printTree(IntLinkedBinaryTree t) {
        Iterator<Position<Integer>> it = t.inorder().iterator();
        System.out.println(); // print empty lines for readability
        // Traverse tree until leaf node (children == null)
        while ( it.hasNext() ) {
            System.out.print(it.next().getElement() + " "); // print stored element
        }
        System.out.println(); // print empty lines for readability
    }

    /**
    * Add a new node with e to the tree rooted at position p.
    * @param p The root of the tree to which new node is added.
    * @param e The element of the new node.
    * @return If a node with e does not exist, a new node with e is added and.
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

        // Loop until leaf node reached
        while( x != null ) {
            // Store elements in the left subtree of 'p' when 'e' is less than p’s element
            if( x.getElement() == e ) {
                return null; // same element
                // Set left node of node passed if 'e' element is smaller than current node element
            } else if ( e < x.getElement()) {
                y = x; // set 'y' placeholder node position as current 'x' node
                x = left(x); // 'x' == 'p', so setting as left node of original Position<Intiger> passed
                // Set right node of node passed if 'e' element is bigger than current node element
            } else {
                y = x; // set 'y' placeholder node position as current 'x' node
                x = right(x); // 'x' == 'p', so setting as right node of original Position<Intiger> passed
            }
        } // --------- END WHILE LOOP ---------

        // Temporary node holding 'e' element,
        Position<Integer> temp = new Node<Integer>(e, null, null, null);
        // If 'e' element is smaller than current node element, add Position<Integer> node as left node of current node element
        if ( e < y.getElement()) {
            addLeft(y, temp.getElement()); // add 'e' element as left node of current node 'y' == original 'p' passed
            // If 'e' element is bigger than current node element, add Position<Integer> node as right node of current node element
        } else {
            addRight(y, temp.getElement()); // add 'e' element as right node of current node 'y' == original 'p' passed
        }
        return temp;
    }

    /**
    * Removes the node with e in the tree rooted at position p.
    * @param Position<Integer> p, the root of the tree from which a node is deleted.
    * @param Integer e, the integer key of the node to be deleted.
    * @return Integer e if integer key exists, null otherwise
    */
    public Integer delete(Position<Integer> p, Integer e) {

        // Loop entire tree
        while (p != null) {
            Integer current = p.getElement();
            if( current.equals(e) ) {
                if (numChildren(p) == 2) {
                    Map<Position<Integer>, Integer> remove = new LinkedHashMap<Position<Integer>, Integer>();
                    Map<Position<Integer>, Integer> positions = remove; // copy positions to add back to tree

                    // Get iterable collection of children Positions from Position p being deleted
                    // Since we can't acces private class "inorderSubtree", make our own list of
                    // children Positions of Position p
                    Iterator<Position<Integer>> subTree = children(p).iterator(); // iterator of Position p's children
                    while (subTree.hasNext()) { // traverse every child
                        Position<Integer> next = subTree.next(); // get a child Position
                        Iterator<Position<Integer>> children = children(next).iterator(); // check/get if child has more children (traverse down)
                        Position<Integer> child = next; // sub-child
                        remove.put(child, child.getElement()); // add for removal
                        while (children.hasNext()) {
                            child = children.next();
                            remove.put(child, child.getElement()); // add for removal
                        }
                    }

                    // Remove all children of to Position p
                    Iterator<Map.Entry<Position<Integer>, Integer>> it = remove.entrySet().iterator(); // create iterator
                    while (it.hasNext()) {
                        Position<Integer> delete = it.next().getKey(); // get Position
                        remove(delete); // remove Positions
                    }
                    remove(p); // finally can remove Position p (no children)

                    // Add back all children Positions to tree
                    it = positions.entrySet().iterator(); // create new iterator of Positions to add back
                    while (it.hasNext()) {
                        Integer add = it.next().getValue(); // get Integer
                        add(root(), add);
                    }
            } else {
                remove(p); // otherwise single child, simple remove
            }
            return e; // always return the element deleted
            } else if ( e < current) {
                p = left(p); // traverse left side of tree (e < p's element)
            } else {
                p = right(p); // traverse right side of tree (e > p's element)
            }
        }
        return null; // key does not exist
    }
}
