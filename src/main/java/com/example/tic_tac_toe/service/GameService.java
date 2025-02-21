package com.example.tic_tac_toe.service;

import org.springframework.stereotype.Service;
import com.example.tic_tac_toe.model.Game;

@Service
public class GameService {
    private Game game = new Game();

    private final int[][] WINNING_COMBINATIONS = {
            { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, // rows
            { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, // columns
            { 0, 4, 8 }, { 2, 4, 6 } // diagonals
    };

    public Game getGame() {
        return game;
    }

    public Game makeMove(int position) {
        int boardSize = game.getSize() * game.getSize();
        if (!game.isGameActive() || position < 0 || position >= boardSize || game.getBoard()[position] != null) {
            return game;
        }

        String currentPlayer = game.isXTurn() ? "X" : "O";
        game.getBoard()[position] = currentPlayer;

        if (checkWin(currentPlayer, position)) {
            game.setGameActive(false);
            game.setStatus(currentPlayer + " Wins!");
            return game;
        }

        if (checkDraw()) {
            game.setGameActive(false);
            game.setStatus("Draw!");
            return game;
        }

        game.setXTurn(!game.isXTurn());
        game.setStatus((game.isXTurn() ? "X" : "O") + "'s turn");
        return game;
    }

    private boolean checkWin(String player, int lastMove) {
        int size = game.getSize();
        int row = lastMove / size;
        int col = lastMove % size;

        // Check row
        boolean rowWin = true;
        for (int c = 0; c < size; c++) {
            if (!player.equals(game.getBoard()[row * size + c])) {
                rowWin = false;
                break;
            }
        }
        if (rowWin)
            return true;

        // Check column
        boolean colWin = true;
        for (int r = 0; r < size; r++) {
            if (!player.equals(game.getBoard()[r * size + col])) {
                colWin = false;
                break;
            }
        }
        if (colWin)
            return true;

        // Check diagonal
        if (row == col) {
            boolean diagWin = true;
            for (int i = 0; i < size; i++) {
                if (!player.equals(game.getBoard()[i * size + i])) {
                    diagWin = false;
                    break;
                }
            }
            if (diagWin)
                return true;
        }

        // Check anti-diagonal
        if (row + col == size - 1) {
            boolean antiDiagWin = true;
            for (int i = 0; i < size; i++) {
                if (!player.equals(game.getBoard()[i * size + (size - 1 - i)])) {
                    antiDiagWin = false;
                    break;
                }
            }
            if (antiDiagWin)
                return true;
        }

        return false;
    }

    public Game resetGame(int size) {
        game = new Game(size);
        return game;
    }

    private boolean checkWin(String player) {
        for (int[] combination : WINNING_COMBINATIONS) {
            if (game.getBoard()[combination[0]] == player &&
                    game.getBoard()[combination[1]] == player &&
                    game.getBoard()[combination[2]] == player) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDraw() {
        for (String cell : game.getBoard()) {
            if (cell == null) {
                return false;
            }
        }
        return true;
    }
}