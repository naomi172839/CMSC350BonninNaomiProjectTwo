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
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
This test checks only if the file is created in the proper location, with the proper name, and with the proper content.
It does not test the correctness of the infix expression as that is tested elsewhere.
 */
public class fileOutputTest implements TestExecutionExceptionHandler {
    BufferedReader reader;
    File file;

    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {

        if (throwable instanceof IOException) {
            return;
        }
        throw throwable;
    }

    @BeforeEach
    void setUp() {
        file = new File("three.txt");
        file.delete();
    }

    @AfterEach
    void tearDown() {
        file = new File("three.txt");
        file.delete();
    }

    //This test asserts that the file was created.  File.delete returns false if file not found.
    @Test
    void testFileCreation() throws IOException {
        Converter.convert("1 2 3 +-");
        assertTrue(file.delete());
        Converter.convert("5 6 7 +-");
        assertTrue(file.delete());
    }

    //This test insures that subsequent conversions have the ability to append a file
    @Test
    void testAbilityToAppend() throws IOException {
        Converter.convert("1 2 3 + -");
        Converter.convert("5 6 7 + -");
        assertTrue(file.delete());
    }

    @Test
    void testSingleContent() throws IOException {
        Converter.convert("1 2 3 + -");
        reader = new BufferedReader(new FileReader("three.txt"));
        ArrayList<String> result = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            result.add(line);
        }
        assertEquals(result.get(1), "Postfix Expression: 1 2 3 + - ");
        assertEquals(result.get(2), "ADD R0 2 3");
        assertEquals(result.get(3), "SUB R1 1 R0");
        assertEquals(result.get(4), "Infix Expression: (1-(2+3))");
    }

    //This test ensure that the results are correctly appended when multiple expressions are given
    @Test
    void testDoubleContent() throws IOException {
        Converter.convert("1 2 3 + -");
        Converter.convert("3 5 7 * /");
        reader = new BufferedReader(new FileReader("three.txt"));
        ArrayList<String> result = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            result.add(line);
        }
        assertEquals(result.get(1), "Postfix Expression: 1 2 3 + - ");
        assertEquals(result.get(2), "ADD R0 2 3");
        assertEquals(result.get(3), "SUB R1 1 R0");
        assertEquals(result.get(4), "Infix Expression: (1-(2+3))");

        assertEquals(result.get(7), "Postfix Expression: 3 5 7 * / ");
        assertEquals(result.get(8), "MUL R0 5 7");
        assertEquals(result.get(9), "DIV R1 3 R0");
        assertEquals(result.get(10), "Infix Expression: (3/(5*7))");
    }


}
