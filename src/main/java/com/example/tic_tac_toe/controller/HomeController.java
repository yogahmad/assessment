package com.example.tic_tac_toe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String landing() {
        return "landing";
    }

    @GetMapping("/game")
    public String game() {
        return "game";
    }
}