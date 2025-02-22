package com.example.tic_tac_toe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tic_tac_toe.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
}