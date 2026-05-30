package com.spaceshooter.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaceshooter.service.GameService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GameStateServlet extends HttpServlet {
    private final GameService gameService;
    private final ObjectMapper objectMapper;

    public GameStateServlet(GameService gameService, ObjectMapper objectMapper) {
        this.gameService = gameService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        objectMapper.writeValue(resp.getWriter(), gameService.buildGameState());
    }
}
