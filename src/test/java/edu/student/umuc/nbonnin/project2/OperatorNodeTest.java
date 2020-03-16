/*
 * Copyright (c) 2020.
 * Author: Naomi Bonnin
 * Class: CMSC 350
 * Project: Project 2
 * Date: 3/15/20, 10:22 PM
 * Description:  Project 2 converts a postfix expression to an infix expression using a binary expression tree.  In addition, a *.txt file is created or appended in the root directory containing the psudo-assembly instructions needed to evaluate the expression.
 */

package edu.student.umuc.nbonnin.project2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OperatorNodeTest {

    OperatorNode n1, n2, n3, n4, c1, c2, c3;

    @BeforeEach
    void setUp() {
        n1 = new OperatorNode("+");
        n2 = new OperatorNode("-");
        n3 = new OperatorNode("*");
        n4 = new OperatorNode("/");
        c1 = new OperatorNode("1");
        c2 = new OperatorNode("2");
        c3 = new OperatorNode("3");
    }

    @AfterEach
    void tearDown() {
        n1 = null;
        n2 = null;
        n3 = null;
        n4 = null;
        c1 = null;
        c2 = null;
        c3 = null;
    }

    @Test
    void getData() {
        assertEquals("+", n1.getData());
        assertEquals("-", n2.getData());
        assertEquals("*", n3.getData());
    }

    @Test
    void setData() {
        n1.setData("*");
        n2.setData("R0");
        n3.setData("Test");
        assertEquals("*", n1.getData());
        assertEquals("R0", n2.getData());
        assertEquals("Test", n3.getData());
    }

    @Test
    void getLeft() {
        n1.setLeft(c1);
        n2.setLeft(c2);
        n3.setLeft(c3);
        assertEquals(n1.getLeft(), c1);
        assertEquals(n2.getLeft(), c2);
        assertEquals(n3.getLeft(), c3);

    }

    @Test
    void setLeft() {
        //Intentionally blank.  Tested above.
    }

    @Test
    void getRight() {
        n1.setRight(c1);
        n2.setRight(c2);
        n3.setRight(c3);
        assertEquals(n1.getRight(), c1);
        assertEquals(n2.getRight(), c2);
        assertEquals(n3.getRight(), c3);
    }

    @Test
    void setRight() {
        //Intentionally blank.  Tested above.
    }

    //Note that even though c1, c2, and c3 do not contain operators, they are still OperatorNode's
    //Therefor the isOperator method will still return true
    @Test
    void isOperator() {
        assertTrue(n1.isOperator());
        assertTrue(n2.isOperator());
        assertTrue(n3.isOperator());
        assertTrue(n4.isOperator());
        assertTrue(c1.isOperator());
        assertTrue(c2.isOperator());
        assertTrue(c3.isOperator());
    }

    //This test also tests the toAssembly private method
    @Test
    void getThreeAdd() {
        assertEquals(n1.getThreeAdd(), "ADD");
        assertEquals(n2.getThreeAdd(), "SUB");
        assertEquals(n3.getThreeAdd(), "MUL");
        assertEquals(n4.getThreeAdd(), "DIV");
        assertEquals(c1.getThreeAdd(), "ERR");
        assertEquals(c2.getThreeAdd(), "ERR");
        assertEquals(c3.getThreeAdd(), "ERR");
    }
}