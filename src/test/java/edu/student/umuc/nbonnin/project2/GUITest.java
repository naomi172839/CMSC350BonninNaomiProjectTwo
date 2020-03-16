/*
 * Copyright (c) 2020.
 * Author: Naomi Bonnin
 * Class: CMSC 350
 * Project: Project 2
 * Date: 3/15/20, 10:22 PM
 * Description:  Project 2 converts a postfix expression to an infix expression using a binary expression tree.  In addition, a *.txt file is created or appended in the root directory containing the psudo-assembly instructions needed to evaluate the expression.
 */

package edu.student.umuc.nbonnin.project2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class GUITest {

    //Note that this test simply tests the ability of the GUI to be created as is without throwing
    //any exceptions.  It does not test layout or functionality of the GUI
    @Test
    void testCreateGUI() {
        try {
            //Tests multiple GUI's to make sure there is no problem with shared resources
            new GUI();
            new GUI();
            Driver.main(new String[0]); //Tests that Driver creates a GUI
        } catch (Exception e) {  //Bad practice but we want to make sure it is created
            fail();
        }
    }

}