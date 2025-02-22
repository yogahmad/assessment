package com.example.tic_tac_toe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data;

@Entity
@Data
public class AIGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String[] board;

    @Column(name = "is_x_turn")
    private boolean isXTurn;

    @Column(name = "game_active")
    private boolean gameActive;

    private String status;

    private int size;

    @Column(name = "player_first")
    private boolean playerFirst;

    private String difficulty;

    public AIGame(int size) {
        this.size = size;
        this.board = new String[size * size];
        this.isXTurn = true;
        this.gameActive = true;
        this.status = "X's turn";
    }

    public AIGame() {
        this(3);
    }
}