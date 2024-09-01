/*
 * Author: Emiliano Huerta
 * Student ID: 80790063
 * Date: April 22, 2024
 * Course: CS1101
 * Instructor: Alireza Nouri
 * Assignment: Comprehensive Lab 3 - WordleGame
 * Description: This class represents the main driver program for the Wordle game. It handles user input, game initialization, and loop for playing the game.
 */

 import java.io.FileNotFoundException;
 import java.util.Scanner;
 
 public class Main {
 
     /* 
      * This method starts the Wordle game. It prompts the user to choose an option from the main menu,
      * initializes the game based on the user's choice, and returns an instance of WordleGame.
      */
     public static WordleGame startGame(Scanner scanner) {
         boolean programOn = true;
         int puzzleNumber = 0;
         String answer = null;
 
         while (programOn) {
             printMainMenu(); // Display the main menu to the user
 
             int choice = getValidIntegerInput(scanner, 1, 4); // Get valid integer input for menu choice
             switch (choice) {
                 case 1:
                     puzzleNumber = puzzleSelector(scanner); // Prompt user to select a puzzle
                     try {
                         answer = WordBank.getAnswerForPuzzleNumber(puzzleNumber); // Get the answer for the selected puzzle from WordBank
                         if (answer != null) {
                             return new WordleGame(answer); // Initialize and return a new instance of WordleGame
                         } else {
                             System.out.println("\nFailed to retrieve answer. Please try again.");
                         }
                     } catch (FileNotFoundException e) {
                         System.out.println("\nFile not found: " + e.getMessage());
                         // Handle the error based on your program's requirements
                     }
                     break;
                 case 2:
                     WordleLetter.printGameRules(); // Display game rules to the user
                     break;
                 case 3:
                     WordleLetter.printExample(); // Display a gameplay example to the user
                     break;
                 case 4:
                     System.out.println("\nExiting the program. Goodbye!");
                     programOn = false; // Exit the program
                     break;
                 default:
                     System.out.println("\nInvalid choice. Please choose again.");
                     break;
             }
         }
 
         return null;
     }
 
     /* 
      * This method prints the main menu to the console, prompting the user to choose an option.
      */
     public static void printMainMenu() {
         String purpleCode = "\u001B[35m"; // ANSI escape code for purple color
         String resetCode = "\u001B[0m"; // ANSI escape code to reset color
 
         System.out.println("\nWelcome to " + purpleCode + "WORDLE!" + resetCode);
         System.out.println("1. Play");
         System.out.println("2. How to Play");
         System.out.println("3. Gameplay Example");
         System.out.println("4. Exit");
         System.out.print("Choose an option: ");
     }
 
     /* 
      * This method prompts the user to choose a puzzle number and returns the selected puzzle number.
      */
     public static int puzzleSelector(Scanner scanner) {
         System.out.println("\nChoose a number from 1-2315, that'll be your Puzzle.");
         return getValidPuzzleNumberInput(scanner, 1, 2315) - 1; // Adjust the selected puzzle number to fit array indexing
     }
 
     /* 
      * This method starts the game loop and allows the user to play the Wordle game.
      */
     public static void playGame(Scanner scanner, WordleGame game) {
         if (game == null) {
             return;
         }
         System.out.println("\nLet's start playing Wordle!");
 
         game.play(); // Start playing the game
     }
 
     /* 
      * This method reports the outcome of the Wordle game.
      */
     public static void reportGameOutcome(WordleGame game) {
         if (game == null) {
             System.out.println("\nNo game played. No outcome to report.\n");
             return;
         }
         // Report the outcome of the game
     }
 
     /* 
      * This main method starts the Wordle game by calling startGame, playGame, and reportGameOutcome methods.
      */
     public static void main(String[] args) {
         /* Only use this Scanner for user input, do not create new ones via new Scanner(System.in). */
         Scanner scanner = new Scanner(System.in);
         WordleGame game = startGame(scanner); // Start the game and initialize a WordleGame object
         playGame(scanner, game); // Start playing the game
         reportGameOutcome(game); // Report the outcome of the game
     }
 
     /* 
      * Helper method to get valid integer input within a specified range.
      */
     public static int getValidIntegerInput(Scanner scanner, int min, int max) {
         while (true) {
             System.out.println("Enter a number between " + min + " and " + max + ".");
             if (scanner.hasNextInt()) {
                 int input = scanner.nextInt();
                 if (input >= min && input <= max) {
                     return input;
                 } else {
                     System.out.println("\nInvalid input. Please try again");
                 }
             } else {
                 System.out.println("\nInvalid input. Please enter a number");
                 scanner.next(); // consume invalid input
             }
         }
     }
 
     /* 
      * Helper method to get valid puzzle number input within a specified range.
      */
     private static int getValidPuzzleNumberInput(Scanner scanner, int min, int max) {
         while (true) {
             System.out.println("Enter a number between " + min + " and " + max + ": ");
             if (scanner.hasNextInt()) {
                 int input = scanner.nextInt();
                 if (input >= min && input <= max) {
                     return input;
                 } else {
                     System.out.println("\nInvalid input. Please enter a number between " + min + " and " + max + ".");
                 }
             } else {
                 System.out.println("\nInvalid input. Please enter a number.");
                 scanner.next(); // consume invalid input
             }
         }
     }
 
 }
 