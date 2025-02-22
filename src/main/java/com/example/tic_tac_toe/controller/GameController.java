package com.example.tic_tac_toe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.tic_tac_toe.model.Game;
import com.example.tic_tac_toe.service.GameService;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public Game getGame() {
        return gameService.getGame();
    }

    @PostMapping("/move/{position}")
    public Game makeMove(@PathVariable int position) {
        return gameService.makeMove(position);
    }

    @PostMapping("/ai-move")
    public Game makeAIMove() {
        Game game = gameService.getGame();
        if (!game.isGameActive()) {
            return game;
        }

        String[] board = game.getBoard();
        List<Integer> emptyCells = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            if (board[i] == null) {
                emptyCells.add(i);
            }
        }

        if (!emptyCells.isEmpty()) {
            int randomMove = emptyCells.get(new Random().nextInt(emptyCells.size()));
            return gameService.makeMove(randomMove);
        }

        return game;
    }

    @PostMapping("/reset/{size}")
    public Game resetGame(@PathVariable int size) {
        return gameService.resetGame(size);
    }

    @PostMapping("/reset")
    public Game resetGame() {
        return gameService.resetGame(3);
    }
}