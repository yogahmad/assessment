package com.example.tic_tac_toe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/")
    public String landing() {
        return "landing";
    }

    @GetMapping("/multiplayer")
    public String multiplayer() {
        return "multiplayer";
    }

    @GetMapping("/ai-game")
    public String aiGame(@RequestParam String player, Model model) {
        if (!player.equals("first") && !player.equals("second")) {
            return "redirect:/";
        }
        model.addAttribute("player", player);
        return "ai-game";
    }
}