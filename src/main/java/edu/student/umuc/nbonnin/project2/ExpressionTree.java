/*
 * Copyright (c) 2020.
 * Author: Naomi Bonnin
 * Class: CMSC 350
 * Project: Project 2
 * Date: 3/15/20, 10:22 PM
 * Description:  Project 2 converts a postfix expression to an infix expression using a binary expression tree.  In addition, a *.txt file is created or appended in the root directory containing the psudo-assembly instructions needed to evaluate the expression.
 */

package edu.student.umuc.nbonnin.project2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Stack;

/*
The ExpressionTree class is responsible for the representation of the ExpressionTree.
It contains 5 instance variables:
Node root which defines the root node,
StringBuilder temp which is used for multi-digit numbers,
BufferedWriter out which is used to write to the file,
String infix which is used to hold the infix representation of the ExpressionTree, and
int rCount which is used to increment the registers.
It contains 1 constructor which takes a single argument,
an array of Strings representing the tokenized postfix expression.
The constructor throws an IOException if the FileWriter is unable to create the three.txt file.
The following methods are implemented:
isOperator takes 1 argument, a String token, and returns true if the token is an operator,
constructTree takes 1 argument, an array of Strings.  It iterates through the array and creates nodes as needed,
inOrder takes 1 argument, a Node n, and recursively traverses the tree and creates the infix expression, and
getInfix takes 0 arguments and returns the infix representation of the ExpressionTree.
 */
public class ExpressionTree {

    //Instance variables.  Ensures creation per ExpressionTree
    private Node root;
    private StringBuilder temp;
    private BufferedWriter out;
    private String infix;
    private int rCount;

    //Single argument constructor, takes an array of Strings.  Creates the tree and initialises the instance variables
    //Writes the psuedo-assembly to a file.
    public ExpressionTree(String[] tokenizedExpression) throws IOException {
        constructTree(tokenizedExpression);  //Creates the tree off of the expression
        temp = new StringBuilder();  //Creates the string builder
        //Create the BufferedWriter.  Also specifies the file name and ensures that the file is appended
        //for multiple expressions.
        out = new BufferedWriter(new FileWriter("three.txt", true));
        rCount = 0;  //Sets the register count to 0
        //Writes the current DateTime to the buffer.
        out.write(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("LLL dd, yyyy HH:mm:ss")) + "\nPostfix Expression: ");
        //Writes the postfix expression to the buffer
        for (String s : tokenizedExpression) {
            out.write(s + " ");
        }
        out.write("\n");
        inOrder(root);  //Performs the conversion from postfix to infix
        infix = temp.toString();
        out.write("Infix Expression: " + infix + "\n\n");  //Write the infix expression to the buffer
        out.flush();  //Writes the buffer to the file
        out.close();  //Frees the buffer's memory

    }

    //Utility method to determine if a token is an operator.  Takes 1 argument, String token, and returns
    //true if the token is an operator.
    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("/") || token.equals("*");
    }

    //Method that creates the expression tree.  Takes 1 argument, an array of Strings, representing the
    //tokenized postfix expression.  Uses a Stack to hold nodes as needed.  Also uses 3 placeholder
    //nodes to temporarily store created nodes.  Sets the expression tree root to be the root of the expression tree
    private void constructTree(String[] expression) {
        Stack<Node> holder = new Stack<>();
        Node parent, left, right;

        //Iterate through expression
        for (String token : expression) {
            if (!isOperator(token)) {  //If token is a digit
                parent = new OperandNode(token);  //Create new operand node from the token
                holder.push(parent); //Pushes node onto stack
            } else { //If token is an operator
                parent = new OperatorNode(token); //Create a new operator node from the token
                right = holder.pop(); //Get the operators right child
                left = holder.pop();  //Get the operators left child

                parent.setLeft(left);
                parent.setRight(right);

                holder.push(parent); //Pushes operator onto the stack
            }
        }
        root = holder.peek();  //Get the root node
        holder.pop();
    }

    //Method that performs recursive in order traversal to generate both the infix expression
    //and the pseudo-assembly expression.  Takes 1 argument, Node n, representing the root node
    //Throws IOException if there is an error in the opening/creating the file.
    private void inOrder(Node n) throws IOException {
        if (n != null) {  //If the Node is not null
            if (n.isOperator()) { //If the Node is an operator
                temp.append("("); //Append opening parenthesis
            }
            inOrder(n.getLeft()); //Recursive loop through the left of tree
            temp.append(n.getData()); // Appends Node value to the StringBuilder
            inOrder(n.getRight());  //Recursive loop through the right of the tree
            if (n.isOperator()) {
                out.write(  //gets the psuedo-assembly operator, and adds the storage register
                        n.getThreeAdd() + " R" + rCount + " "
                                + n.getLeft().getData() + " " + n.getRight().getData() + "\n");
                n.setData("R" + rCount);  //Now that the node is processed, replaces data with resulting register
                rCount++;
            }
            if (n.isOperator()) { //If node is an operator
                temp.append(")"); //Add closing parenthesis
            }
        }
    }

    //Method to return the infix expression
    public String getInfix() {
        return infix;
    }
}
