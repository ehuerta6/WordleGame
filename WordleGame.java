/*
 * Author: Emiliano Huerta
 * Student ID: 80790063
 * Date: April 22, 2024
 * Course: CS1101
 * Instructor: Alireza Nouri
 * Assignment: Comprehensive Lab 3 - WordleGame
 * Description: This class represents the Wordle game, where players guess a five-letter word. It provides methods for playing the game, processing guesses, and resetting the game.
 */

 import java.awt.*;
 import java.io.FileNotFoundException;
 import java.util.Scanner;
 import javax.swing.*;

 public class WordleGame {
     private WordleLetter[][] allGuesses = new WordleLetter[5][5]; // Changed to 5 rows
     private String answer; // The correct answer for the current puzzle
     private int attemptsLeft = 5; // Changed to 5 attempts
     private Scanner scanner = new Scanner(System.in); // Scanner for user input
     private JFrame frame;
     private JPanel gamePanel;
     private JTextField guessField;
     private JButton submitButton;
     private JLabel messageLabel;
     private JPanel[][] letterPanels;
     private WordBank wordBank;

     /*
      * Constructor to initialize a WordleGame object with the answer for the current puzzle.
      */
     public WordleGame(String answer) throws FileNotFoundException {
         this.answer = answer;
         this.wordBank = new WordBank();
         createAndShowGUI();
         
         // Print the correct answer in the console for testing
         System.out.println("Correct answer (for testing): " + answer);
     }

     private void createAndShowGUI() {
         frame = new JFrame("Wordle Game");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(400, 600);
         frame.setLayout(new BorderLayout(10, 10));

         gamePanel = new JPanel(new GridLayout(5, 5, 5, 5));
         gamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
         letterPanels = new JPanel[5][5];

         for (int i = 0; i < 5; i++) {
             for (int j = 0; j < 5; j++) {
                 JPanel letterPanel = new JPanel(new GridBagLayout());
                 letterPanel.setBackground(Color.WHITE);
                 letterPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                 letterPanel.setPreferredSize(new Dimension(50, 50));
                 gamePanel.add(letterPanel);
                 letterPanels[i][j] = letterPanel;
             }
         }

         guessField = new JTextField(5);
         guessField.setFont(new Font("Arial", Font.BOLD, 18));
         submitButton = new JButton("Submit");
         submitButton.setFont(new Font("Arial", Font.BOLD, 14));
         messageLabel = new JLabel("Enter your guess:");
         messageLabel.setFont(new Font("Arial", Font.BOLD, 16));

         JPanel inputPanel = new JPanel();
         inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
         inputPanel.add(messageLabel);
         inputPanel.add(guessField);
         inputPanel.add(submitButton);

         frame.add(gamePanel, BorderLayout.CENTER);
         frame.add(inputPanel, BorderLayout.SOUTH);

         submitButton.addActionListener(e -> processGuess(guessField.getText()));
         guessField.addActionListener(e -> processGuess(guessField.getText()));

         frame.setVisible(true);
     }

     /*
      * Method to start playing the Wordle game.
      */
     public void play() {
         // This method is now empty as the game flow is handled by the GUI
     }

     /*
      * Method to process the user's guess.
      */
     public void processGuess(String guess) {
         guess = guess.toLowerCase();
         if (guess.length() != 5) {
             JOptionPane.showMessageDialog(frame, "Please enter a 5-letter word.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
             return;
         }

         if (!wordBank.isValidWord(guess)) {
             JOptionPane.showMessageDialog(frame, "Not a valid word. Please try again.", "Invalid Word", JOptionPane.WARNING_MESSAGE);
             return;
         }

         // Reset the allGuesses array for the current attempt
         allGuesses[5 - attemptsLeft] = new WordleLetter[5];

         // Initialize variables to count green and yellow tiles
         int greenCount = 0;
         int yellowCount = 0;

         // Check each letter of the guess against the answer
         for (int i = 0; i < guess.length(); i++) {
             char guessLetter = guess.charAt(i);
             char answerLetter = answer.charAt(i);

             // Check if the letters match exactly
             if (guessLetter == answerLetter) {
                 // Place a green tile
                 allGuesses[5 - attemptsLeft][i] = new WordleLetter(guessLetter);
                 allGuesses[5 - attemptsLeft][i].setColor("green");
                 greenCount++;
             } else if (answer.indexOf(guessLetter) != -1) {
                 // Place a yellow tile
                 allGuesses[5 - attemptsLeft][i] = new WordleLetter(guessLetter);
                 allGuesses[5 - attemptsLeft][i].setColor("yellow");
                 yellowCount++;
             } else {
                 // Place a red tile
                 allGuesses[5 - attemptsLeft][i] = new WordleLetter(guessLetter);
                 allGuesses[5 - attemptsLeft][i].setColor("red");
             }
         }

         // Console output
         System.out.println("\nFeedback:");
         WordleLetter.feedbackInColor(greenCount, yellowCount, (5 - greenCount - yellowCount));
         System.out.println("Your guess with corresponding colors");
         for (int i = 0; i < allGuesses[5 - attemptsLeft].length; i++) {
             WordleLetter letter = allGuesses[5 - attemptsLeft][i];
             System.out.print(letter);
         }
         System.out.println();

         // Update GUI
         for (int i = 0; i < 5; i++) {
             JPanel panel = letterPanels[5 - attemptsLeft][i];
             panel.removeAll();
             JLabel letterLabel = new JLabel(String.valueOf(guess.charAt(i)).toUpperCase());
             letterLabel.setFont(new Font("Arial", Font.BOLD, 24));
             letterLabel.setForeground(Color.WHITE);
             panel.add(letterLabel);
             panel.setBackground(getColorForLetter(allGuesses[5 - attemptsLeft][i]));
             panel.revalidate();
             panel.repaint();
         }

         if (isWordCorrect(guess)) {
             showGameOverDialog("YOU WON!");
         } else {
             attemptsLeft--;
             if (attemptsLeft == 0) {
                 showGameOverDialog("YOU LOSE!\nThe word was: " + answer.toUpperCase());
             } else {
                 updateAttemptsDisplay();
             }
         }

         guessField.setText("");
     }

     /*
      * Method to check if the guessed word is correct.
      */
     private boolean isWordCorrect(String guess) {
         return guess.equalsIgnoreCase(answer); // Ignore case when comparing the guess with the answer
     }

     /*
      * Method to reset the game for another round.
      */
     private void resetGame() {
         attemptsLeft = 5;
         allGuesses = new WordleLetter[5][5];

         for (JPanel[] row : letterPanels) {
             for (JPanel panel : row) {
                 panel.removeAll();
                 panel.setBackground(Color.WHITE);
                 panel.revalidate();
                 panel.repaint();
             }
         }

         guessField.setEnabled(true);
         submitButton.setEnabled(true);
         messageLabel.setText("Enter your guess:");
         messageLabel.setForeground(Color.BLACK);

         // Choose a new word
         try {
             int newPuzzleNumber = (int) (Math.random() * 2315);
             answer = WordBank.getAnswerForPuzzleNumber(newPuzzleNumber);
             System.out.println("New correct answer (for testing): " + answer);
         } catch (FileNotFoundException e) {
             JOptionPane.showMessageDialog(frame, "Error loading new word. Exiting game.", "Error", JOptionPane.ERROR_MESSAGE);
             frame.dispose();
             System.exit(1);
         }
     }

     private Color getColorForLetter(WordleLetter letter) {
         String color = letter.getColor();
         switch (color) {
             case "green":
                 return new Color(106, 170, 100); // Green color
             case "yellow":
                 return new Color(201, 180, 88); // Yellow color
             case "red":
                 return new Color(220, 20, 60); // Red color
             default:
                 return new Color(120, 124, 126); // Gray color for incorrect letters
         }
     }

     private void showGameOverDialog(String message) {
         guessField.setEnabled(false);
         submitButton.setEnabled(false);
         int choice = JOptionPane.showConfirmDialog(frame, 
             message + "\n\nDo you want to play again?", 
             "Game Over", 
             JOptionPane.YES_NO_OPTION);
         
         if (choice == JOptionPane.YES_OPTION) {
             resetGame();
         } else {
             frame.dispose();
             System.exit(0);
         }
     }

     private void updateAttemptsDisplay() {
         Color attemptsColor;
         String consoleColor;

         if (attemptsLeft <= 2) {
             attemptsColor = new Color(220, 20, 60); // Red
             consoleColor = WordleLetter.ANSI_RED;
         } else if (attemptsLeft <= 4) {
             attemptsColor = new Color(201, 180, 88); // Yellow
             consoleColor = WordleLetter.ANSI_YELLOW;
         } else {
             attemptsColor = new Color(106, 170, 100); // Green
             consoleColor = WordleLetter.ANSI_GREEN;
         }

         // Update GUI
         messageLabel.setText("Attempts left: " + attemptsLeft);
         messageLabel.setForeground(attemptsColor);

         // Print attempts left in console with color
         System.out.println(consoleColor + "Attempts left: " + attemptsLeft + WordleLetter.ANSI_RESET);
     }
 }
