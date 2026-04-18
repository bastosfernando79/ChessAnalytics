package com.chess.analytics.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChessComStatsDto {
    @JsonProperty("chess_rapid")
    private ChessRapid chessRapid;

    @Data
    public static class ChessRapid {
        private Last last;
        private Record record;
    }

    @Data
    public static class Last {
        private Integer rating;
    }

    @Data
    public static class Record {
        private Integer win;
        private Integer loss;
        private Integer draw;
    }
}