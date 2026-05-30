package com.spaceshooter.controller;

import com.spaceshooter.model.GameState;
import com.spaceshooter.service.GameService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        String playerName = (String) session.getAttribute("playerName");
        if (playerName == null) {
            return "redirect:/login";
        }
        model.addAttribute("playerName", playerName);
        model.addAttribute("state", gameService.buildGameState());
        return "index";
    }

    @GetMapping("/hangar")
    public String hangar(Model model, HttpSession session) {
        String playerName = (String) session.getAttribute("playerName");
        if (playerName == null) {
            return "redirect:/login";
        }
        model.addAttribute("playerName", playerName);
        model.addAttribute("ships", gameService.getShips());
        model.addAttribute("upgrades", gameService.getUnlockedUpgrades());
        return "hangar";
    }

    @GetMapping("/api/state")
    @ResponseBody
    public GameState apiState() {
        return gameService.buildGameState();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              HttpSession session,
                              Model model) {
        if ("dhyan".equals(username) && "1234".equals(password)) {
            session.setAttribute("playerName", username);
            return "redirect:/";
        }
        model.addAttribute("error", "Invalid username or password.");
        return "login";
    }
}
