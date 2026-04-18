package com.chess.analytics.service;

import com.chess.analytics.dto.ChessComStatsDto;
import com.chess.analytics.dto.DashboardResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ChessIntegrationService {

    private final WebClient webClient;
    private static final int TARGET_RATING = 1500; // Sua meta configurada

    // O Spring injeta automaticamente o WebClient no construtor
    public ChessIntegrationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.chess.com/pub/player/").build();
    }

    public DashboardResponseDto getPlayerDashboard(String username) {
        // 1. Faz a requisição na PubAPI do Chess.com
        ChessComStatsDto stats = webClient.get()
                .uri(username + "/stats")
                .retrieve()
                .bodyToMono(ChessComStatsDto.class)
                .block(); // .block() porque estamos fazendo uma API síncrona simples por enquanto

        if (stats == null || stats.getChessRapid() == null) {
            throw new RuntimeException("Jogador não encontrado ou sem histórico de partidas rápidas");
        }

        // 2. Extrai os dados
        int currentRating = stats.getChessRapid().getLast().getRating();
        int wins = stats.getChessRapid().getRecord().getWin();
        int losses = stats.getChessRapid().getRecord().getLoss();
        int draws = stats.getChessRapid().getRecord().getDraw();

        // 3. Regras de Negócio (Cálculos)
        int totalGames = wins + losses + draws;
        double winRate = (totalGames > 0) ? ((double) wins / totalGames) * 100 : 0.0;
        int pointsToGoal = Math.max(0, TARGET_RATING - currentRating); // Evita números negativos se já passou da meta

        // 4. Monta a resposta final usando o Builder do Lombok
        return DashboardResponseDto.builder()
                .username(username)
                .currentRating(currentRating)
                .targetRating(TARGET_RATING)
                .pointsToGoal(pointsToGoal)
                .winRatePercentage(Math.round(winRate * 100.0) / 100.0) // Arredonda para 2 casas decimais
                .totalGames(totalGames)
                .build();
    }
}