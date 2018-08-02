/**
* InfixEvaluation.java
* This class implements LinkedStack to solve infix arithmetic expressions.
* It takes in a file where each line contains an expression without parenthesis.
* Finds the correct order of operations and calculates solution.
* @author  Andrés Bretón
* @version 1.0
*/

/**
SOURCE:
    http://www.java2novice.com/data-structures-in-java/stacks/infix-expression/
    https://www.geeksforgeeks.org/expression-evaluation/
    Sections using examples as source of inspiration from above source denoted with commented lines "** SOURCE **"
NOTE:
    Sections for purpuse of debugging and learning denoted with commented lines  "// ** DEBUG **"
    Most commented out code (a lot of it here) was used for my learning purposes and left for future comprehension. Sorry!
*/

import java.io.*;
import java.util.Scanner;

public class InfixEvaluation {
    /**
    * Main method parsing the input file, where each line is an arithmetic
    * expression (without parenthesis). It evaluates the value of the infix
    * notation expression using a LinkedStack data structure and prints results.
    */
    public static void main(String[] args) throws IOException {
        // Get file content
        Scanner fileInput = new Scanner(new File("hw5_input.txt"));
        // Traverse through file content
        while (fileInput.hasNext()) {
            // Get line as string
            String expression = fileInput.nextLine();
            // Print expression and results by calling 'evaluate' method
            System.out.println("The value of " + expression + " is " + evaluate(expression));
        }
    }

    /**
    * Method used to evaluate the expression using a LinkedStack data structure.
    * @param String expression.
    * @return int value of evaluated expression.
    */
    public static int evaluate(String expression) {
        // Split expression by space(s) into array of strings
        String[] tokens = expression.split("\\s+");
        // Stack for operands (numbers)
        LinkedStack<Integer> values = new LinkedStack<Integer>();
        // Stack for operators
        LinkedStack<String> operators = new LinkedStack<String>();
        // Length of expression
        int numTokens = tokens.length;

        // Loop through expression and determine type (operator or value)
        // Store in LinkedStack
        for (int i = 0; i < numTokens ; i++ ) {
            // Set token as current character of expression
            String token = tokens[i];
            // Check if token is a number, cause you never know...
            // Uses method to check if value is an integer with try/catch
            if (checkInt(token)) {
                // System.out.println("FOUND number: " + number); // ** DEBUG **

                // Since token is a number, turn it into integer
                int number = Integer.parseInt(token);
                values.push(number); // store in LinkedStack
                // Else, it should be an operator
            } else if (token.equals("*") || token.equals("/") || token.equals("+") || token.equals("-")) {
                // Token is an operator
                // System.out.println("Current token: " + token); // ** DEBUG **

                // While the operators stack is not empty, check for current and previous operator's precedences
                // Only evaluates to true when previous operator has precedence over current
                // ** SOURCE **
                while ( !operators.isEmpty() && getPrecedence(tokens[i], operators.top()) ) {
                    // System.out.println("Previous operator " + operators.top() + " has precedence over current " + token); // ** DEBUG **
                    // System.out.println("Operator stack not empty!" + operators + values); // ** DEBUG **
                    // System.out.println("Using top operator: " + operators.top()); // ** DEBUG **
                    // System.out.println("Using top value: " + values.top()); // ** DEBUG **

                    /**
                    Use the previous operator to calculate last 2 operands (numbers) stored
                    Push value calculated with operator precedence into account
                    This only occurs when 2 conditions met:
                    1.) Operator list is not empty -- We have at least 1 operator stored (by-passes 1st operator)
                    2.) Previous stored operator has precedence over current operator
                    */
                    int answer = calculate(operators.pop(), values.pop(), values.pop());
                    // System.out.println("Value stored = " + answer); // ** DEBUG **
                    values.push( answer );
                }
                // System.out.println("Storing token -- " + token); // ** DEBUG **

                // Push current operator to LinkedStack
                operators.push(token);
            }
        }
        // System.out.println("Let's start calculating the remaining expression..."); // ** DEBUG **

        // Expression completely parsed at this point, calculate remaining expression
        while (!operators.isEmpty()) {
            // System.out.println(operators); // ** DEBUG **
            // System.out.println(values); // ** DEBUG **
            // System.out.println("Using top operator: " + operators.top()); // ** DEBUG **
            // System.out.println("Using top value: " + values.top()); // ** DEBUG **
            values.push( calculate(operators.pop(), values.pop(), values.pop()) );
        }
        return values.pop();
    }

    /**
    * Method checking if passed token is type int. It returns true if int, otherwise false.
    * @return boolean.
    */
    public static boolean checkInt(String token) {
        try {
            int number = Integer.parseInt(token);
            return true;
        }
        catch(Exception e) { return false; }
    }

    /**
    * Method checking if current operator has precedence over last.
    * It returns false when current has precedence over last.
    * @return boolean.
    */
    // ** SOURCE **
    public static boolean getPrecedence(String op1, String op2) {
        // System.out.println("Checking precedence of " + op2 + " over " + op1); // ** DEBUG **

        // If multiplication/division is after addition/subtraction, then false
        if ((op1.equals("*") || op1.equals("/")) && (op2.equals("+") || op2.equals("-")))
            return false;
        else
            return true;
    }

    /**
    * Method to apply arithmetic expression on two values passed, returning result.
    * Since we can only pop top values, first value passed is second operand (b) and
    * second value poped is first operand (a) when calculating arithmetic expression.
    */
    // ** SOURCE **
    public static int calculate(String operator, int b, int a) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0)
                    throw new UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }
}
