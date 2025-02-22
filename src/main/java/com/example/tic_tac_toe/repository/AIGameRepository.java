package com.example.tic_tac_toe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tic_tac_toe.model.AIGame;

public interface AIGameRepository extends JpaRepository<AIGame, Long> {
}
