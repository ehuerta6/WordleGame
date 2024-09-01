/*
 * Author: Emiliano Huerta
 * Student ID: 80790063
 * Date: April 22, 2024
 * Course: CS1101
 * Instructor: Alireza Nouri
 * Assignment: Comprehensive Lab 3 - WordleGame
 * Description: This class manages the storage and retrieval of words for the Wordle game. It provides methods to access a bank of words used for guessing in the game.
 */

 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.Scanner;
 
 public class WordBank {
 
     /*
      * This method retrieves the answer for a specific puzzle number from the answers.txt file.
      * It takes an integer puzzleNumber as input and returns the corresponding answer.
      * Throws FileNotFoundException if the file is not found or if the specified puzzle number exceeds the available puzzles.
      */
     public static String getAnswerForPuzzleNumber(int puzzleNumber) throws FileNotFoundException {
         File file = new File("answers.txt"); // Create a File object for the answers.txt file
         Scanner scanner = new Scanner(file); // Create a Scanner object to read from the file
 
         for (int i = 0; i < puzzleNumber; i++) {
             if (!scanner.hasNext()) {
                 scanner.close();
                 throw new FileNotFoundException("File not found: answers.txt"); // Throw exception if file not found or if puzzle number exceeds available puzzles
             }
             scanner.next();
         }
 
         if (!scanner.hasNext()) {
             scanner.close();
             throw new FileNotFoundException("File not found: answers.txt"); // Throw exception if file not found or if puzzle number exceeds available puzzles
         }
         String answer = scanner.next(); // Retrieve the answer for the specified puzzle number
         scanner.close(); // Close the scanner
         return answer; // Return the answer
     }
 
     /*
      * This method checks if the proposed word exists in the dictionary.
      * It takes a String proposed as input and returns true if the word exists in the dictionary, false otherwise.
      */
     public static boolean checkInDictionary(String proposed) {
         File dictionaryFile = new File("dictionary.txt"); // Create a File object for the dictionary.txt file
         try (Scanner scanner = new Scanner(dictionaryFile)) { // Use try-with-resources to automatically close the scanner
             while (scanner.hasNextLine()) { // Iterate through each line in the dictionary
                 String word = scanner.nextLine().trim(); // Get the word from the current line and trim any leading or trailing whitespace
                 if (word.equalsIgnoreCase(proposed)) { // Check if the proposed word matches the current word in the dictionary (case-insensitive)
                     return true; // Return true if the word is found in the dictionary
                 }
             }
         } catch (FileNotFoundException e) { // Catch FileNotFoundException if the dictionary file is not found
             System.err.println("File not found: dictionary.txt"); // Print error message
         }
         return false; // Return false if the word is not found in the dictionary
     }
 }
 