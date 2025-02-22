package com.example.tic_tac_toe.model;

import lombok.Data;

@Data
public class Game {
    private String[] board;
    private boolean isXTurn;
    private boolean gameActive;
    private String status;
    private int size;

    public Game(int size) {
        this.size = size;
        this.board = new String[size * size];
        this.isXTurn = true;
        this.gameActive = true;
        this.status = "X's turn";
    }

    public Game() {
        this(3);
    }
}