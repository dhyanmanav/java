package com.spaceshooter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaceshooter.service.GameService;
import com.spaceshooter.servlet.GameStateServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletRegistrationConfig {

    @Bean
    public ServletRegistrationBean<GameStateServlet> gameStateServlet(GameService gameService, ObjectMapper objectMapper) {
        GameStateServlet servlet = new GameStateServlet(gameService, objectMapper);
        ServletRegistrationBean<GameStateServlet> bean = new ServletRegistrationBean<>(servlet, "/servlet/state");
        bean.setName("gameStateServlet");
        return bean;
    }
}
