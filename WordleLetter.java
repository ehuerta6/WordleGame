/*
 * Author: Emiliano Huerta
 * Student ID: 80790063
 * Date: April 22, 2024
 * Course: CS1101
 * Instructor: Alireza Nouri
 * Assignment: Comprehensive Lab 3 - WordleGame
 * Description: This class represents the WordleLetter object, which stores information about a letter in the Wordle game. It provides methods for setting and getting the letter's color, as well as printing it with color codes.
 */

 public class WordleLetter {
    private char letter; // The letter stored by the WordleLetter object
    private String color; // The color assigned to the letter

    /*
     * Constructor to initialize a WordleLetter object with a specified letter.
     */
    public WordleLetter(char letterIn) {
        this.letter = letterIn;
    }

    /*
     * Method to set the color of the WordleLetter object.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /*
     * Method to return a string representation of the WordleLetter object with color codes.
     */
    public String toString() {
        String colorCode;
        switch (color) {
            case "green":
                colorCode = "\u001B[32m"; // Green color code
                break;
            case "yellow":
                colorCode = "\u001B[33m"; // Yellow color code
                break;
            case "blue":
                colorCode = "\u001B[34m"; // Blue color code
                break;
            default:
                colorCode = "\u001B[31m"; // Red color code (default for incorrect letters)
                break;
        }
        String resetCode = "\u001B[0m"; // Reset color code
        return String.format("%s %s %s", colorCode, letter, resetCode);
    }

    /*
     * Method to print a variable in blue color.
     */
    public static String printVariableInColor(String variable) {
        String blueCode = "\u001B[36m"; // Blue color code
        String resetCode = "\u001B[0m"; // Reset color code
        return blueCode + variable + resetCode;
    }

    /*
     * Method to print the number of attempts left with color codes based on the number.
     */
    public static String printAttemptsLeft(int attemptsLeft) {
        String colorCode;
        if (attemptsLeft >= 1 && attemptsLeft <= 2) {
            colorCode = "\u001B[31m"; // Red color code
        } else if (attemptsLeft >= 3 && attemptsLeft <= 4) {
            colorCode = "\u001B[33m"; // Yellow color code
        } else {
            colorCode = "\u001B[36m"; // Blue color code
        }
        String resetCode = "\u001B[0m"; // Reset color code
        return colorCode + attemptsLeft + resetCode;
    }

    /*
     * Method to print the number of attempts left with color codes in reverse order based on the number.
     */
    public static String printAttemptsLeftReverse(int attemptsLeft) {
        String colorCode;
        if (attemptsLeft >= 1 && attemptsLeft <= 2) {
            colorCode = "\u001B[34m"; // Blue color code
        } else if (attemptsLeft >= 3 && attemptsLeft <= 4) {
            colorCode = "\u001B[33m"; // Yellow color code
        } else {
            colorCode = "\u001B[31m"; // Red color code
        }
        String resetCode = "\u001B[0m"; // Reset color code
        return colorCode + attemptsLeft + resetCode;
    }    

    /*
     * Method to print feedback for the user with color-coded counts of correct and incorrect letters.
     */
    public static void feedbackInColor(int greenCount, int yellowCount, int incorrectCount) {
        String colorCode;
        String resetCode = "\u001B[0m"; // Reset color code
    
        // Print feedback for green count
        colorCode = "\u001B[32m"; // Green color code
        if (greenCount == 0) {
            System.out.println(colorCode + "None" + resetCode + " of the letters are correct and in the " + colorCode + "right position." + resetCode);
        } else if (greenCount == 1) {
            System.out.println(colorCode + "1" + resetCode + " letter is correct and in the " + colorCode + "right position." + resetCode);
        } else {
            System.out.println(colorCode + greenCount + resetCode + " letters are correct and in the " + colorCode + "right position." + resetCode);
        }
    
        // Print feedback for yellow count
        colorCode = "\u001B[33m"; // Yellow color code
        if (yellowCount == 0) {
            System.out.println(colorCode + "None" + resetCode + " of the letters are correct but in the " + colorCode + "wrong position." + resetCode);
        } else if (yellowCount == 1) {
            System.out.println(colorCode + "1" + resetCode + " letter is correct but in the " + colorCode + "wrong position." + resetCode);
        } else if (yellowCount == 5) {
            System.out.println(colorCode + "All" + resetCode + " letters are correct but in the " + colorCode + "wrong position." + resetCode);
        } else {
            System.out.println(colorCode + yellowCount + resetCode + " letters are correct but in the " + colorCode + "wrong position." + resetCode);
        }
    
        // Print feedback for incorrect count
        colorCode = "\u001B[31m"; // Red color code
        if (incorrectCount == 0) {
            System.out.println(colorCode + "None" + resetCode + " of the letters are " + colorCode + "incorrect." + resetCode);
        } else if (incorrectCount == 1) {
            System.out.println(colorCode + "1" + resetCode + " letter is " + colorCode + "incorrect." + resetCode);
        } else if (incorrectCount == 5) {
            System.out.println(colorCode + "All" + resetCode + " letters are " + colorCode + "incorrect." + resetCode);
        } else {
            System.out.println(colorCode + incorrectCount + resetCode + " letters are " + colorCode + "incorrect." + resetCode);
        }
    }

    /*
     * Method to print the game rules with color formatting.
     */
    public static void printGameRules() {
        String cyanCode = "\u001B[36m";
        String greenCode = "\u001B[32m";
        String yellowCode = "\u001B[33m";
        String redCode = "\u001B[31m";
        String purpleCode = "\u001B[35m";
        String resetCode = "\u001B[0m";
    
        System.out.println("\nHow to play " + purpleCode + "WORDLE?" + resetCode);
        System.out.println("-. Each guess must be a " + cyanCode + "valid five-letter word" + resetCode + ".");
        System.out.println("-. The " + cyanCode + "color of a tile" + resetCode + " will change to show you " + cyanCode + "how close your guess was" + resetCode + ".");
        System.out.println("-. If the tile turns " + greenCode + "green" + resetCode + ", " + greenCode + "the letter is in the word," + resetCode + " and " + greenCode + "in the correct spot" + resetCode + ".");
        System.out.println("-. If the tile turns " + yellowCode + "yellow" + resetCode + ", " + yellowCode + "the letter is in the word," + resetCode + " but " + yellowCode + "not in the correct spot" + resetCode + ".");
        System.out.println("-. If the tile turns " + redCode + "red" + resetCode + ", " + redCode + "the letter is not in the word" + resetCode + ".");
    }

    /*
     * Method to print a gameplay example with color formatting.
     */
    public static void printExample() {
        // Define ANSI color codes
        String purpleColor = "\u001B[35m"; // Purple color code for legend
        String blueCode = "\u001B[36m"; // Blue color code for user input
        String greenCode = "\u001B[32m"; // Green color code for correct letters
        String redCode = "\u001B[31m"; // Red color code for incorrect letters
        String yellowCode = "\u001B[33m";
        String resetCode = "\u001B[0m"; // Reset color code       
        String cyanCode = "\u001B[36m";

        // Print the gameplay example with color formatting
        System.out.println(blueCode + "\nStart of Gameplay Example" + resetCode);
        System.out.println("Choose a number from 1-2315, that'll be your Puzzle.");
        System.out.println("Enter a number between 1 and 2315: ");
        System.out.println(blueCode + "20" + resetCode + purpleColor + " // Number of the Chosen Puzzle" + resetCode);

        System.out.println("\nLet's start playing Wordle!\n");

        System.out.println("Game Started! You have 6 attempts to guess the word.\n")
        ;
        System.out.println("Attempts left: " + cyanCode + "6" + resetCode + purpleColor + " // Attempts left\n" + resetCode);
        System.out.println("Enter your guess (5-letter word): ");
        System.out.println(blueCode + "plank" + resetCode + purpleColor + " // First Try\n" + resetCode);

        System.out.println("Feedback:");
        System.out.println(greenCode + "1" + resetCode + " letter is correct and in the " + greenCode + "right position." + resetCode);
        System.out.println(yellowCode + "None " + resetCode + "of the letters are correct but in the " + yellowCode + "wrong position." + resetCode);
        System.out.println(redCode + "4 " + resetCode + "letters are " + redCode + "incorrect."+ resetCode);
        System.out.println("Your guess with corresponding colors");
        System.out.println(" " + redCode + "p" + resetCode + "  " + redCode + "l" + resetCode + "  " + 
        redCode + "a" + resetCode + "  " + greenCode + "n" + resetCode + "  " + redCode + "k" + resetCode + purpleColor + " // Guess with color Feeback\n" + resetCode);

        System.out.println("Attempts left: " + cyanCode + "5" + resetCode + purpleColor + " // Attempts left\n" + resetCode);
        System.out.println("Enter your guess (5-letter word):");
        System.out.println(blueCode + "icing" + resetCode + purpleColor + " // Second Try\n" + resetCode);

        System.out.println("Feedback:");
        System.out.println(greenCode + "5" + resetCode + " letters are correct and in the " + greenCode + "right position." + resetCode);
        System.out.println(yellowCode + "None " + resetCode + "of the letters are correct but in the " + yellowCode + "wrong position." + resetCode);
        System.out.println(redCode + "None " + resetCode + "of the letters are " + redCode + "incorrect."+ resetCode);
        System.out.println("Your guess with corresponding colors");
        System.out.println(" " + greenCode + "i" + resetCode + "  " + greenCode + "c" + resetCode + "  " +
         greenCode + "i" + resetCode + "  " + greenCode + "n" + resetCode + "  " + greenCode + "g" + resetCode + purpleColor + " // Guess with color Feeback\n" + resetCode);

        System.out.println("Congratulations! You guessed the word: " + blueCode + "icing" + resetCode);
        System.out.println("You solved the puzzle using " + blueCode + "1" + resetCode + " attempt.");
        System.out.println(blueCode + "End of Gameplay Example" + resetCode);
    }    
}
