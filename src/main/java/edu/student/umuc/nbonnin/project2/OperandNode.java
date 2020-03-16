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
The OperandNode class extends the abstract class Node.  All of the superclass methods (the getters and setters)
are inherited from the super class.
In addition, the isOperator method is implemented and returns false.
The threeAdd method is implemented that returns ERR indicating that an error occurred.
A single constructor exists which takes 1 argument, a String data that is sent to the superclass constructor.
In addition, a method toInt is implemented but is not used in order to convert the operand from a string to an int.
In the future, this will be used to evaluate the expression.
 */
public class OperandNode extends Node {

    //Single argument constructor.  Uses the superclass constructor
    public OperandNode(String data) {
        super(data);
    }

    //Implements the abstract class isOperator.  Always returns false
    public boolean isOperator() {
        return false;
    }

    //Implements the abstract getThreeAdd method.  Returns ERR as the node contains a digit
    @Override
    public String getThreeAdd() {
        return "ERR";
    }

    //Converts the Node to an int.  Returns an Integer object
    public int toInt() {
        return Integer.parseInt(this.getData());
    }
}
