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

import static org.junit.jupiter.api.Assertions.*;

//Note that the inherited methods were tested in OperatorNodeTest
class OperandNodeTest {

    OperandNode n1, n2, n3, n4;

    @BeforeEach
    void setUp() {
        n1 = new OperandNode("15");
        n2 = new OperandNode("5");
        n3 = new OperandNode("100000");
        n4 = new OperandNode("+");
    }

    @AfterEach
    void tearDown() {
        n1 = null;
        n2 = null;
        n3 = null;
        n4 = null;
    }

    //Note that isOperator should return false even if the node contains an operator because
    //it is still an OperandNode
    @Test
    void isOperator() {
        assertFalse(n1.isOperator());
        assertFalse(n2.isOperator());
        assertFalse(n3.isOperator());
        assertFalse(n4.isOperator());
    }

    //Note that getThreeAdd should always return ERR when called from an operand node
    //even if the node contains an operator
    @Test
    void getThreeAdd() {
        assertEquals(n1.getThreeAdd(), "ERR");
        assertEquals(n2.getThreeAdd(), "ERR");
        assertEquals(n3.getThreeAdd(), "ERR");
        assertEquals(n4.getThreeAdd(), "ERR");
    }

    @Test
    void toInt() {
        assertEquals(n1.toInt(), 15);
        assertEquals(n2.toInt(), 5);
        assertEquals(n3.toInt(), 100000);
        assertThrows(NumberFormatException.class, () -> n4.toInt());
    }
}