/*
 * Author: Emiliano Huerta
 * Student ID: 80790063
 * Date: April 22, 2024
 * Course: CS1101
 * Instructor: Alireza Nouri
 * Assignment: Comprehensive Lab 3 - WordleGame
 * Description: This class represents the Wordle game, where players guess a five-letter word. It provides methods for playing the game, processing guesses, and resetting the game.
 */

 import java.util.Scanner;
 import java.io.FileNotFoundException;
 
 public class WordleGame {
     private WordleLetter[][] allGuesses = new WordleLetter[6][5]; // Array to store all guesses
     private String answer; // The correct answer for the current puzzle
     private int attemptsLeft = 6; // Number of attempts left for the current puzzle
     private Scanner scanner = new Scanner(System.in); // Scanner for user input
 
     /*
      * Constructor to initialize a WordleGame object with the answer for the current puzzle.
      */
     public WordleGame(String answer) {
         this.answer = answer;
     }
 
     /*
      * Method to start playing the Wordle game.
      */
     public void play() {
         while (true) {
             System.out.println("\nGame Started! You have 6 attempts to guess the word.");
             System.out.println(answer); // For testing purposes, to know what puzzle you selected and check if it works correctly
 
             while (attemptsLeft > 0) {
                 System.out.println("\nAttempts left: " + WordleLetter.printAttemptsLeft(attemptsLeft));
                 System.out.println("Enter your guess (5-letter word): ");
                 String guess = scanner.nextLine();
 
                 if (guess.length() != 5) {
                     System.out.println("\nInvalid guess. Please enter a 5-letter word.");
                     continue;
                 }
 
                 if (!WordBank.checkInDictionary(guess)) { // Check if the guessed word is in the dictionary
                     System.out.println("\nNot a valid word. Please enter a word from the dictionary.");
                     continue;
                 }
 
                 processGuess(guess); // Process the guess
 
                 if (isWordCorrect(guess)) {
                     System.out.println("\nCongratulations! You guessed the word: " + WordleLetter.printVariableInColor(answer) + "\n");
                     if(attemptsLeft==6){
                         System.out.println("You solved the puzzle with " + WordleLetter.printAttemptsLeft(attemptsLeft) + " attempts remaining.");
                     }else if(attemptsLeft==5){
                         System.out.println("You solved the puzzle using " + WordleLetter.printAttemptsLeftReverse(6 - attemptsLeft) + " attempt.");
                     }else{
                         System.out.println("You solved the puzzle using " + WordleLetter.printAttemptsLeftReverse(6 - attemptsLeft) + " attempts.");
                     }
                     break;
                 } else {
                     attemptsLeft--;
                     if (attemptsLeft == 0) {
                         System.out.println("\nSorry, you're out of attempts. The word was: " + WordleLetter.printVariableInColor(answer));
                         break;
                     }
                 }
             }
 
             while (true) {
                 System.out.println("\nWould you like to play again? (yes/no): ");
                 String playAgain = scanner.nextLine().toLowerCase();
                 if (playAgain.equals("yes")) {
                     resetGame(); // Reset the game for another round
                     break;
                 } else if (playAgain.equals("no")) {
                     System.out.println("\nExiting the program. Goodbye!\n");
                     return; // Exit the program
                 } else {
                     System.out.println("\nInvalid input. Please enter 'yes' or 'no'.");
                 }
             }
         }
     }
 
     /*
      * Method to process the user's guess.
      */
     public void processGuess(String guess) {
         guess = guess.toLowerCase(); // Convert guess to lowercase
 
         // Reset the allGuesses array for the current attempt
         allGuesses[6 - attemptsLeft] = new WordleLetter[5];
 
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
                 allGuesses[6 - attemptsLeft][i] = new WordleLetter(guessLetter);
                 allGuesses[6 - attemptsLeft][i].setColor("green");
                 greenCount++;
             } else if (answer.indexOf(guessLetter) != -1) {
                 // Place a yellow tile
                 allGuesses[6 - attemptsLeft][i] = new WordleLetter(guessLetter);
                 allGuesses[6 - attemptsLeft][i].setColor("yellow");
                 yellowCount++;
             } else {
                 // Place a red tile
                 allGuesses[6 - attemptsLeft][i] = new WordleLetter(guessLetter);
                 allGuesses[6 - attemptsLeft][i].setColor("red");
             }
         }
 
         // Fill the rest of the row with gray tiles
         for (int i = guess.length(); i < 5; i++) {
             allGuesses[6 - attemptsLeft][i] = new WordleLetter(' ');
             allGuesses[6 - attemptsLeft][i].setColor("gray");
         }
 
         // Print feedback for the user
         System.out.println("\nFeedback:");
         WordleLetter.feedbackInColor(greenCount, yellowCount, (5 - greenCount - yellowCount));
 
         // Print the guess with corresponding colors
         System.out.println("Your guess with corresponding colors");
         for (int i = 0; i < allGuesses[6 - attemptsLeft].length; i++) {
             WordleLetter letter = allGuesses[6 - attemptsLeft][i];
             System.out.print(letter);
         }
         System.out.println(); // Add newline for spacing
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
         attemptsLeft = 6; // Reset the number of attempts left
         allGuesses = new WordleLetter[6][5]; // Reset the array to store guesses
 
         while (true) {
             System.out.println("\nChoose a new number from 1-2315 for the new puzzle:");
             if (scanner.hasNextInt()) {
                 int newPuzzleNumber = scanner.nextInt();
                 scanner.nextLine(); // Consume newline
                 if (newPuzzleNumber >= 1 && newPuzzleNumber <= 2315) {
                     // Valid puzzle number, update the answer and break out of the loop
                     try {
                         answer = WordBank.getAnswerForPuzzleNumber(newPuzzleNumber - 1); // Adjust for array indexing
                         return;
                     } catch (FileNotFoundException e) {
                         System.out.println("\nFile not found: " + e.getMessage());
                         // Handle the error based on your program's requirements
                     }
                 } else {
                     System.out.println("\nInvalid input. Please enter a number between 1 and 2315.");
                 }
             } else {
                 System.out.println("\nInvalid input. Please enter a number.");
                 scanner.next(); // Consume invalid input
             }
         }
     }
 }
 