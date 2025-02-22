package com.example.tic_tac_toe.controller;

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

    @PostMapping("/reset/{size}")
    public Game resetGame(@PathVariable int size) {
        return gameService.resetGame(size);
    }

    @PostMapping("/reset")
    public Game resetGame() {
        return gameService.resetGame(3);
    }
}