package com.demomvc.curso.boot.dto;

import java.util.List;

public class MatchlistDTO {
    private Integer totalGames;
    private List<MatchReferenceDTO> matches;

    public Integer getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(Integer totalGames) {
        this.totalGames = totalGames;
    }

    public List<MatchReferenceDTO> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchReferenceDTO> matches) {
        this.matches = matches;
    }
}
