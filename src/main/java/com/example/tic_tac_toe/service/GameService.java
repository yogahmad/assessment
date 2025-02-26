package com.example.tic_tac_toe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tic_tac_toe.model.Game;
import com.example.tic_tac_toe.repository.GameRepository;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public Long createMultiplayerGame(int size) {
        Game game = new Game(size);
        Game savedGame = gameRepository.save(game);
        return savedGame.getId();
    }

    public Game makeMultiplayerMove(Long gameId, int row, int column) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Game not found"));

        if (!game.isGameActive()) {
            throw new IllegalArgumentException("Game is already finished");
        }

        int position = row * game.getSize() + column;

        if (row < 0 || row >= game.getSize() || column < 0 || column >= game.getSize()) {
            throw new IllegalArgumentException("Invalid move: Position out of bounds");
        }

        String[] board = game.getBoard();
        if (board[position] != null) {
            throw new IllegalArgumentException("Invalid move: Position already taken");
        }

        board[position] = game.isXTurn() ? "X" : "O";
        game.setXTurn(!game.isXTurn());

        checkGameStatus(game);

        return gameRepository.save(game);
    }

    private void checkGameStatus(Game game) {
        String winner = checkWinner(game);
        if (winner != null) {
            game.setGameActive(false);
            game.setStatus(winner + " wins!");
        } else if (isBoardFull(game.getBoard())) {
            game.setGameActive(false);
            game.setStatus("Draw!");
        } else {
            game.setStatus((game.isXTurn() ? "X" : "O") + "'s turn");
        }
    }

    private boolean isBoardFull(String[] board) {
        for (String cell : board) {
            if (cell == null)
                return false;
        }
        return true;
    }

    private String checkWinner(Game game) {
        String[] board = game.getBoard();
        int size = game.getSize();

        // Check rows
        for (int i = 0; i < size; i++) {
            if (checkLine(board, i * size, 1, size)) {
                return board[i * size];
            }
        }

        // Check columns
        for (int i = 0; i < size; i++) {
            if (checkLine(board, i, size, size)) {
                return board[i];
            }
        }

        // Check diagonals
        if (checkLine(board, 0, size + 1, size)) {
            return board[0];
        }
        if (checkLine(board, size - 1, size - 1, size)) {
            return board[size - 1];
        }

        return null;
    }

    private boolean checkLine(String[] board, int start, int increment, int count) {
        String symbol = board[start];
        if (symbol == null)
            return false;

        for (int i = 1; i < count; i++) {
            if (board[start + i * increment] == null || !board[start + i * increment].equals(symbol)) {
                return false;
            }
        }
        return true;
    }
}