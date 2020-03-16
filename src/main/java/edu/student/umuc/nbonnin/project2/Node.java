/*
 * Copyright (c) 2020.
 * Author: Naomi Bonnin
 * Class: CMSC 350
 * Project: Project 2
 * Date: 3/15/20, 10:22 PM
 * Description:  Project 2 converts a postfix expression to an infix expression using a binary expression tree.  In addition, a *.txt file is created or appended in the root directory containing the psudo-assembly instructions needed to evaluate the expression.
 */

package edu.student.umuc.nbonnin.project2;

/*
The Node abstract class defines one node of the Binary Expression Tree.
It contains 3 instance variables:
String data containing either the operator or operand,
Node left containing the left child node, if any,
and Node right containing the right child node if any.
There is a single constructor taking 1 argument:
String data which is assigned to the instance variable data.
The Left and Right children are instantiated as null ensuring that they can be queried.
Standard getters and setters exist for all instance variables.
2 abstract methods exist:
isOperator which will be used to determine if a Node represents an operator,
and getThreeAdd which will be used to get the three letter assembly representation.
 */
public abstract class Node {

    //Instance variables
    private String data;
    private Node left;
    private Node right;

    //Single argument constructor
    public Node(String data) {
        this.data = data;
        left = null;
        right = null;
    }

    //Getter for data.  Returns a String.
    public String getData() {
        return data;
    }

    //Setter for data.  Takes a String as an argument.
    public void setData(String data) {
        this.data = data;
    }

    //Getter for the Left child.  Returns a Node object.
    public Node getLeft() {
        return left;
    }

    //Setter for the Left child.  Takes a Node as an argument.
    public void setLeft(Node left) {
        this.left = left;
    }

    //Getter for the Right child.  Returns a Node object.
    public Node getRight() {
        return right;
    }

    //Setter for the Right child.  Takes a Node as an argument
    public void setRight(Node right) {
        this.right = right;
    }

    //Abstract class, no implementation here
    public abstract boolean isOperator();

    //Abstract class, no implementation here
    public abstract String getThreeAdd();
}
