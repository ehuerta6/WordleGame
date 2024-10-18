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
 import java.util.HashSet;
 import java.util.Scanner;
 import java.util.Set;

 public class WordBank {
     private static final String WORD_FILE = "dictionary.txt";
     private Set<String> validWords;

     public WordBank() throws FileNotFoundException {
         validWords = new HashSet<>();
         loadWords();
     }

     private void loadWords() throws FileNotFoundException {
         File file = new File(WORD_FILE);
         Scanner scanner = new Scanner(file);
         while (scanner.hasNextLine()) {
             String word = scanner.nextLine().trim().toLowerCase();
             if (word.length() == 5) {
                 validWords.add(word);
             }
         }
         scanner.close();
     }

     public boolean isValidWord(String word) {
         return validWords.contains(word.toLowerCase());
     }

     public static String getAnswerForPuzzleNumber(int puzzleNumber) throws FileNotFoundException {
         File file = new File(WORD_FILE);
         Scanner scanner = new Scanner(file);
         String answer = "";
         int currentLine = 0;
         while (scanner.hasNextLine() && currentLine <= puzzleNumber) {
             answer = scanner.nextLine().trim().toLowerCase();
             currentLine++;
         }
         scanner.close();
         return answer;
     }
 }
