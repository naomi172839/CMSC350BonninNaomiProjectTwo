/*
 * Copyright (c) 2020.
 * Author: Naomi Bonnin
 * Class: CMSC 350
 * Project: Project 2
 * Date: 3/15/20, 10:22 PM
 * Description:  Project 2 converts a postfix expression to an infix expression using a binary expression tree.  In addition, a *.txt file is created or appended in the root directory containing the psudo-assembly instructions needed to evaluate the expression.
 */

package edu.student.umuc.nbonnin.project2;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/*
The GUI class is responsible for creating the GUI that the user interacts with.  It has no public methods and contains
one constructor which takes no arguments.  Upon calling the constructor, the GUI is created and displayed to the user.
Closing the GUI will terminate the application.
 */
public class GUI {

    //Creates the dimensions for the JFrame.
    static final private Dimension d = new Dimension(600, 200);

    // Constructor to create and show the GUI
    public GUI() {
        JFrame frame = new JFrame("Three Address Generator");
        frame.setPreferredSize(d); //Sets the size to the above dimension
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(threeGen()); // This line adds the actual content to the frame
        frame.pack();  //Removes any whitespace and ensure everything is where it should be
        frame.setLocationRelativeTo(null); //Centers the window on the screen
        frame.setVisible(true);  //Shows the window
    }

    // Method to create the actual content for the application
    private JPanel threeGen() {
        // Creates a panel to hold the content
        JPanel content = new JPanel(new GridBagLayout());

        // A constraints object to hold layout information
        GridBagConstraints c = new GridBagConstraints();

        // Different font for readability
        Font f = new Font("Monaco", Font.PLAIN, 16);

        JLabel expressionLabel = new JLabel("Enter Postfix Expression:");
        expressionLabel.setFont(f);

        JLabel resultLabel = new JLabel("Infix Expression:");
        resultLabel.setFont(f);

        // Create expression textfield, changes the font and adds a tooltip
        JTextField expressionText = new JTextField();
        expressionText.setFont(f);
        expressionText.setToolTipText(
                "Please enter a valid integer postfix expression. Valid symbols: +-*/()");
        expressionText.setBorder(BorderFactory.createEmptyBorder());

        //Inserts the expression textField into a scrollPane for long expressions
        JScrollPane scrollExpression = new JScrollPane(expressionText);
        scrollExpression.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollExpression.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);


        // Creates result textfield, makes it uneditable, change font and makes transparent
        JTextField resultText = new JTextField();
        resultText.setEditable(false);
        resultText.setFont(f);
        resultText.setBackground(content.getBackground());
        resultText.setBorder(BorderFactory.createEmptyBorder());

        //Inserts the result textField into a scrollPane for long expressions.  Sets the background to
        //transparent.
        JScrollPane scrollResult = new JScrollPane(resultText);
        scrollResult.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollResult.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollResult.setBackground(content.getBackground());

        JButton evaluate = new JButton("Construct Tree");
        evaluate.setFont(f);

        // Sets the constraints for the expression label and adds it to the panel
        c.anchor = GridBagConstraints.LINE_END;
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.insets = new Insets(5, 0, 5, 5);
        c.weightx = 0.25;
        c.weighty = 1;
        content.add(expressionLabel, c);

        // Sets the constraints for the expression textfield and adds it to the panel
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 6;
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = 3;
        c.weighty = 1;
        content.add(scrollExpression, c);

        // Sets constraints for the evaluate button, creates an action listener, and adds it to the
        // panel
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 7;
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = 1;
        c.weighty = 0.5;
        // Adds a new action listener to the button to determine when an event occurs
        evaluate.addActionListener(
                e -> {
                    try {
                        resultText.setText(Converter.convert(expressionText.getText()));  //Performs the conversion
                    } catch (RuntimeException | IOException error) {
                        JOptionPane.showMessageDialog(content, "Invalid Expression: " + error.getMessage());
                    }
                });

        content.add(evaluate, c);

        // Sets the constraints for the result label and adds it to the panel
        c.anchor = GridBagConstraints.LINE_END;
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.insets = new Insets(5, 0, 5, 5);
        c.weightx = 0.25;
        c.weighty = 1;
        content.add(resultLabel, c);

        // Sets the constraints for the result textfield and adds it to the panel
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 6;
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = 3;
        c.weighty = 1;
        content.add(scrollResult, c);

        return content;
    }
}
