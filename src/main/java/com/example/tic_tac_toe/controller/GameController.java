package com.example.tic_tac_toe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.tic_tac_toe.model.AIGame;
import com.example.tic_tac_toe.model.Game;
import com.example.tic_tac_toe.service.AIGameService;
import com.example.tic_tac_toe.service.GameService;

@RestController
@RequestMapping("/api/game")
public class GameController {
    @Autowired
    private GameService gameService;
    @Autowired
    private AIGameService aiGameService;

    @PostMapping("/multiplayer/create")
    public Long createMultiplayerGame(@RequestParam int size) {
        return gameService.createMultiplayerGame(size);
    }

    @PostMapping("/multiplayer/{gameId}/move")
    public ResponseEntity<?> makeMultiplayerMove(
            @PathVariable Long gameId,
            @RequestParam int row,
            @RequestParam int column) {
        try {
            Game game = gameService.makeMultiplayerMove(gameId, row, column);
            return ResponseEntity.ok(game);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/vs-ai/create")
    public Long createAIGame(
            @RequestParam int size,
            @RequestParam boolean isFirstPlayer,
            @RequestParam(defaultValue = "MEDIUM") String difficulty) {
        return aiGameService.createGame(size, isFirstPlayer, difficulty);
    }

    @PostMapping("/vs-ai/{gameId}/move/player")
    public ResponseEntity<?> makePlayerMoveInAIGame(
            @PathVariable Long gameId,
            @RequestParam int row,
            @RequestParam int column) {
        try {
            AIGame game = aiGameService.makePlayerMove(gameId, row, column);
            return ResponseEntity.ok(game);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/vs-ai/{gameId}/move/ai")
    public ResponseEntity<?> makeAIMove(@PathVariable Long gameId) {
        try {
            AIGame game = aiGameService.makeAIMove(gameId);
            return ResponseEntity.ok(game);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
