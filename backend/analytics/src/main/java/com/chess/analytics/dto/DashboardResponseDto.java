package com.chess.analytics.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardResponseDto{
    private String username;
    private Integer currentRating;
    private Integer targetRating;
    private Integer pointsToGoal;
    private Double winRatePercentage;
    private Integer totalGames;
}