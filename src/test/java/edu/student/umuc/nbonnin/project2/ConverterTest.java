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

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

//The converter class is a utility class that ties everything together.  This method ensure
//that Parser, ExpressionTree, OperatorNode, OperandNode and Node are all able to talk and work together.
class ConverterTest {

    String e0, e1, e2, e3, e4, e5;

    @BeforeEach
    void setUp() {
        e0 = "1 2 +"; //Normal Expression
        e1 = "12           5          3 +              -            "; //String with whitespace
        e2 = "5 4 3 +-"; //String without whitespace between operators
        e3 = "1 2 3 4 5 + - * /"; //String with all 4 operators
        e4 = "12a 4 3 + -";  //String with invalid token (digit then character)
        e5 = "12 13 15 + %"; //String with invalid operator
    }

    @AfterEach
    void tearDown() {
        e0 = null;
        e1 = null;
        e2 = null;
        e3 = null;
        e4 = null;
        e5 = null;
    }

    @Test
    void convertValid() {
        try {
            assertEquals(Converter.convert(e0), "(1+2)");
            assertEquals(Converter.convert(e1), "(12-(5+3))");
            assertEquals(Converter.convert(e2), "(5-(4+3))");
            assertEquals(Converter.convert(e3), "(1/(2*(3-(4+5))))");
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void convertInvalid() {
        RuntimeException x4 = assertThrows(RuntimeException.class, () -> Converter.convert(e4));
        RuntimeException x5 = assertThrows(RuntimeException.class, () -> Converter.convert(e5));
        assertEquals(x4.getMessage(), "a");  //Ensures the exception is passing along the offending character
        assertEquals(x5.getMessage(), "%");  //Same as above
    }
}