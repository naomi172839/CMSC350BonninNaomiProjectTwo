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
The OperatorNode class extends the abstract class Node.
It contains 2 additional instance variables not inherited from the superclass:
operator which is a boolean that is set to true if the Node represents an operator,
and a String threeAdd representing the the three letter assembly instruction for the operator.
All of the superclass methods (the getters and setters) are inherited from the super class.
In addition, the isOperator method is implemented and returns the value of operator.
The threeAdd method is implemented that returns the threeAdd representation of the operator.
A private method that converts the operator to the assembly representation also exists to perform the conversion.
A single constructor exists which takes 1 argument, a String data that is sent to the superclass constructor.
The operator value is set to true and the assembly representation is calculated.
 */
public class OperatorNode extends Node {

    //Additional instance variables
    private boolean operator;
    private String threeAdd;

    //Single argument constructor.  Sets operator to true and runs toAssembly method
    public OperatorNode(String data) {
        super(data);
        operator = true;
        threeAdd = toAssembly(data);
    }

    //Implements the abstract isOperator.  Returns value of operator
    public boolean isOperator() {
        return operator;
    }

    //Converts the symbolic representation of the operator to the three address version
    private String toAssembly(String data) {
        switch (data) {
            case ("+"):
                return "ADD";
            case ("-"):
                return "SUB";
            case ("*"):
                return "MUL";
            case ("/"):
                return "DIV";
            default:
                return "ERR";
        }

    }

    //Implements teh abstract getThreeAdd method.  Returns the three address version of the operator
    public String getThreeAdd() {
        return threeAdd;
    }
}
