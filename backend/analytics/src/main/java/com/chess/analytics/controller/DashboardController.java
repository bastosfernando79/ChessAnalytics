package com.chess.analytics.controller;

import com.chess.analytics.dto.DashboardResponseDto;
import com.chess.analytics.service.ChessIntegrationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/analytics")
public class DashboardController {

    private final ChessIntegrationService service;

    public DashboardController(ChessIntegrationService service) {
        this.service = service;
    }

    // Mantemos o ping para testes
    @GetMapping("/ping")
    public String ping() {
        return "Backend do Chess Analytics rodando perfeitamente!";
    }

    // Novo endpoint de verdade!
    @GetMapping("/dashboard/{username}")
    public DashboardResponseDto getDashboard(@PathVariable String username) {
        return service.getPlayerDashboard(username);
    }
}