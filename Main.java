/*
 * Author: Emiliano Huerta
 * Student ID: 80790063
 * Date: April 22, 2024
 * Course: CS1101
 * Instructor: Alireza Nouri
 * Assignment: Comprehensive Lab 3 - WordleGame
 * Description: This class represents the main driver program for the Wordle game. It handles user input, game initialization, and loop for playing the game.
 */

import java.awt.*;
import java.io.FileNotFoundException;
import javax.swing.*;

public class Main {
    private static WordleGame game;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowMainMenu();
        });
    }

    private static void createAndShowMainMenu() {
        JFrame frame = new JFrame("Wordle - Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel titleLabel = new JLabel("Wordle Game", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel, gbc);

        JButton playButton = createStyledButton("Play");
        JButton rulesButton = createStyledButton("How to Play");
        JButton exampleButton = createStyledButton("Gameplay Example");
        JButton exitButton = createStyledButton("Exit");

        panel.add(playButton, gbc);
        panel.add(rulesButton, gbc);
        panel.add(exampleButton, gbc);
        panel.add(exitButton, gbc);

        frame.add(panel, BorderLayout.CENTER);

        playButton.addActionListener(e -> startNewGame());
        rulesButton.addActionListener(e -> showRules());
        exampleButton.addActionListener(e -> showExample());
        exitButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(200, 40));
        return button;
    }

    private static void startNewGame() {
        try {
            int puzzleNumber = (int) (Math.random() * 2315);
            String answer = WordBank.getAnswerForPuzzleNumber(puzzleNumber);
            game = new WordleGame(answer);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private static void showRules() {
        JEditorPane editorPane = new JEditorPane("text/html", WordleLetter.getGameRulesText());
        editorPane.setEditable(false);
        editorPane.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Game Rules", JOptionPane.PLAIN_MESSAGE);
    }

    private static void showExample() {
        JEditorPane editorPane = new JEditorPane("text/html", WordleLetter.getExampleText());
        editorPane.setEditable(false);
        editorPane.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Gameplay Example", JOptionPane.PLAIN_MESSAGE);
    }
}
