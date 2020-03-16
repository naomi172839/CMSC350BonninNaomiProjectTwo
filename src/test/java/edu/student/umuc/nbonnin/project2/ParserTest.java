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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    String e0, e1, e2, e3, e4, e5, e6;
    Parser p0, p1, p2, p3, p4, p5, p6;

    @BeforeEach
    void setUp() {
        e0 = "1 2 3 + -"; //Normal Expression
        e1 = "12           5          3 +              -            "; //String with whitespace
        e2 = "5 4 3+-"; //String without whitespace between operators
        e3 = "1 2 3 4 5 + - * /"; //String with all 4 operators
        e4 = "12a 4 3 + -";  //String with invalid token (digit then character)
        e5 = "12 13 15 + %"; //String with invalid operator
        e6 = "12 2 1 + -+";  //String with too many operators
    }

    @AfterEach
    void tearDown() {
        e1 = null;
        e2 = null;
        e3 = null;
        e4 = null;
        e5 = null;
        e6 = null;
    }

    @Test
    void testParse() {
        p0 = new Parser(e0);  //Should not throw exception
        p1 = new Parser(e1);  //Should not throw exception
        p2 = new Parser(e2);  //Should not throw exception
        p3 = new Parser(e3);  //Should not throw exception
        assertThrows(RuntimeException.class, () -> p4 = new Parser(e4));
        assertThrows(RuntimeException.class, () -> p5 = new Parser(e5));
        assertThrows(RuntimeException.class, () -> p6 = new Parser(e6));
    }

    @Test
    void getParsedExpression() {
        p0 = new Parser(e0);
        p1 = new Parser(e1);
        p2 = new Parser(e2);
        p3 = new Parser(e3);
        assertArrayEquals(p0.getParsedExpression(), new String[]{"1", "2", "3", "+", "-"});
        assertArrayEquals(p1.getParsedExpression(), new String[]{"12", "5", "3", "+", "-"});
        assertArrayEquals(p2.getParsedExpression(), new String[]{"5", "4", "3", "+", "-"});
        assertArrayEquals(p3.getParsedExpression(), new String[]{"1", "2", "3", "4", "5", "+", "-", "*", "/"});
    }
}