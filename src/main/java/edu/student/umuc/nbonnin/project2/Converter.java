/*
 * Copyright (c) 2020.
 * Author: Naomi Bonnin
 * Class: CMSC 350
 * Project: Project 2
 * Date: 3/15/20, 10:22 PM
 * Description:  Project 2 converts a postfix expression to an infix expression using a binary expression tree.  In addition, a *.txt file is created or appended in the root directory containing the psudo-assembly instructions needed to evaluate the expression.
 */
package edu.student.umuc.nbonnin.project2;
import java.io.IOException;

/*
Utility class to separate the conversion logic from the GUI.
Has a private constructor to prevent instantiation.
Has a single public method that creates a parser and expression tree then returns the infix expression.
Calls constructors for both so all logic is performed.
Takes a single argument, representing the user given postfix expression
 */
public final class Converter {

    //Private constructor to prevent instantiation
    private Converter() {
        //Intentionally Blank
    }

    //Performs the conversion by creating the appropriate objects.  Takes the expression as an argument
    //and returns the infix expression.
    public static String convert(String expression) throws IOException, RuntimeException {
        Parser p = new Parser(expression);
        ExpressionTree t = new ExpressionTree(p.getParsedExpression());
        return t.getInfix();
    }
}
