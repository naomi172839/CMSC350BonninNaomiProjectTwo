/*
 * Copyright (c) 2020.
 * Author: Naomi Bonnin
 * Class: CMSC 350
 * Project: Project 2
 * Date: 3/15/20, 3:38 PM
 * Description:  Project 2 converts a postfix expression to an infix expression using a binary expression tree.  In addition, a *.txt file is created or appended in the root directory containing the psudo-assembly instructions needed to evaluate the expression.
 */

import java.util.ArrayList;

/*
The Parser class is used to convert a plain text string into an array of strings with each element representing a
single token, be it operator or operand.
It has 2 instance variables:
String initialExpression representing the postfix expression as given by the user, and
String[] parsedExpression representing the postfix expression converted into an array.
It has 1 constructor which takes 1 argument, String userExpression, and initiate the parsing of the expression.
It throws a runtime exception which it receives from the method parse.  This exception is handled in the GUI class.
It has a method, parse, which iterates through the string and converts it to the array based on whitespace
delimiters between digits.
It has a method getParsedExpression, which simply returns the parsed expression as a String[].
 */
public class Parser {

    //Instance variables
    private String[] parsedExpression;
    private String initialExpression;

    //Single argument constructor.  Saves initial expression and calls parse method
    public Parser(String userExpression) throws RuntimeException {
        this.initialExpression = userExpression;
        parse();
    }

    //Method with no arguments and no return.  Throws RuntimeException if there are any invalid characters
    //in the initial expression.  Sets the parsedExpression instance variable or if there is an invalid ratio
    //between operators and operands.  A valid expression will contain 1 less operator than operands.
    private void parse() throws RuntimeException {
        //Used to hold the expression as it is less costly than determining the size needed for the array
        ArrayList<String> expression = new ArrayList<>();
        //StringBuilder used in case digits are more than one character long.
        StringBuilder temp = new StringBuilder();
        int dCount = 0, oCount = 0;  //Initial operand and operator counts

        for (char token : initialExpression.toCharArray()) { //Convert string to char array and iterate through
            if (Character.toString(token).matches("\\d")) { //If the character is a digit
                temp.append(token); //Add digit to the StringBuilder
                continue; //Next token
            }
            if (Character.toString(token).matches("\\s")) { //If character is a space
                if (!temp.toString().isEmpty()) {  //Checks to see if anything is in StringBulder
                    expression.add(temp.toString()); //Saved digit
                    temp = new StringBuilder(); //Resets StringBuilder
                    dCount++; //Notes that a digit was saved
                }
                continue;
            }
            if (Character.toString(token).matches("[+\\-*/]")) { //If character is one of the 4 operators
                if (!temp.toString().isEmpty()) { //If StringBulder has a digit, save the digit
                    expression.add(temp.toString());
                    temp = new StringBuilder(); //Resets StringBuilder
                    dCount++; //Notes a digit was save
                }
                expression.add(Character.toString(token)); //Save the operand
                oCount++; //Note that an operater was saved
            } else { //If character is not a digit, space or one of the 4 operators, throws exception
                throw new RuntimeException(Character.toString(token));
            }
        }
        if (dCount - oCount != 1) { //If the ratio is incorrect, throw exception
            throw new RuntimeException("Invalid Operator to Operand Ratio");
        }
        parsedExpression = expression.toArray(new String[0]); //Convert ArrayList to single sized array
    }

    //Getter for parsedExpression.  Returns String[]
    public String[] getParsedExpression() {
        return parsedExpression;
    }
}
