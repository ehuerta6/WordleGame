# Wordle Game in Java

## Overview
The **Wordle Game in Java** is a console-based word-guessing game where players attempt to guess a hidden five-letter word within a set number of tries. This project uses multiple classes to handle game logic, color codes for feedback, and external files to load the word pool.

## Features
- **Guess Feedback**: Provides real-time feedback on guessed words using color codes:
  - Green for correct letters in the right position.
  - Yellow for correct letters in the wrong position.
  - Gray for incorrect letters.
- **File Handling**: Reads word lists from external files (`answers.txt` and `dictionary.txt`) for game customization.
- **Multiple Classes**: Game logic is split into separate classes for better organization and code maintainability.
  
## Files
- **`Main.java`**: The entry point for the application, handles the overall game flow.
- **`WordleGame.java`**: Contains the core logic of the game, including word checking and feedback generation.
- **`WordBank.java`**: Manages word lists and reads from external files (`answers.txt` and `dictionary.txt`).
- **`WordleLetter.java`**: Represents individual letters in the game, tracking their status (correct, incorrect, or misplaced).
- **`answers.txt`**: Contains the list of possible answers for the game.
- **`dictionary.txt`**: Contains a broader list of valid words players can guess.

## How to Run
1. Clone the repository and navigate to the project directory.
2. Ensure `answers.txt` and `dictionary.txt` are in the same directory as the `.java` files.
3. Compile and run the Java program:
    ```bash
    javac Main.java WordleGame.java WordBank.java WordleLetter.java
    java Main
    ```
4. Play the game by guessing five-letter words through the console.

