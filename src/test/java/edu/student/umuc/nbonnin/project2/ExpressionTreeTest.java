/*
 * Copyright (c) 2020.
 * Author: Naomi Bonnin
 * Class: CMSC 350
 * Project: Project 2
 * Date: 3/15/20, 10:22 PM
 * Description:  Project 2 converts a postfix expression to an infix expression using a binary expression tree.  In addition, a *.txt file is created or appended in the root directory containing the psudo-assembly instructions needed to evaluate the expression.
 */

package edu.student.umuc.nbonnin.project2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ExpressionTreeTest {

    String[] e0, e1, e2, e3, e4;
    ExpressionTree t0, t1, t2, t3, t4;

    @BeforeEach
    void setUp() {
        e0 = new String[]{"1", "2", "+"};
        e1 = new String[]{"12", "5", "3", "+", "-"};
        e2 = new String[]{"5", "4", "3", "+", "-"};
        e3 = new String[]{"1", "2", "3", "4", "5", "+", "-", "*", "/"};
        e4 = new String[]{"1", "2", "3", "+", "4", "5", "-", "+", "-"};
    }

    //This is the only real method that can be tested for ExpressionTree.  Note that this will test many different
    //parts of the class at once.  Where possible, every effort was made to seperate the tests based off of
    //which part of the class is being tested.
    @Test
    void getInfix() {
        try {
            t0 = new ExpressionTree(e0);
            t1 = new ExpressionTree(e1);
            t2 = new ExpressionTree(e2);
            t3 = new ExpressionTree(e3);
            t4 = new ExpressionTree(e4);
            assertEquals(t0.getInfix(), "(1+2)");  //Tests creation of infix expression
            assertEquals(t1.getInfix(), "(12-(5+3))"); //Tests handling multi-digit numbers
            assertEquals(t2.getInfix(), "(5-(4+3))"); //Tests proper parenthesis placement
            assertEquals(t3.getInfix(), "(1/(2*(3-(4+5))))");  //Tests the isOperator method
            assertEquals(t4.getInfix(), "(1-((2+3)+(4-5)))"); //Further parenthesis placement
        } catch (IOException e) {  //Tests the ability to create/append and write to a file
            fail();
        }
    }
}