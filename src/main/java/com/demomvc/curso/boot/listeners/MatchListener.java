package com.demomvc.curso.boot.listeners;

import com.demomvc.curso.boot.domain.Invocador;
import com.demomvc.curso.boot.domain.Partida;
import com.demomvc.curso.boot.dto.MatchReferenceDTO;
import com.demomvc.curso.boot.dto.MatchlistDTO;
import com.demomvc.curso.boot.service.PartidaService;
import com.demomvc.curso.boot.service.RiotApiService;

import java.util.List;

public class MatchListener implements EventListener {
    private PartidaService partidaService;
    private RiotApiService riotApiService;

    public MatchListener(PartidaService partidaService, RiotApiService riotApiService) {
        this.partidaService = partidaService;
        this.riotApiService = riotApiService;
    }

    @Override
    public void run(String eventType, Invocador invocador) throws Exception {
        switch (eventType) {
            case "createSummoner":
                this.create(invocador);
                break;
            case "updateSummoner":
                this.update(invocador);
                break;
            case "deleteSummoner":
                this.delete(invocador);
                break;
        }
    }

    public void create(Invocador invocador) throws Exception {
        MatchlistDTO matchlistDTO = riotApiService.getSummonerMatchlist(invocador.getAccountId(), invocador.getRegion());

        for (MatchReferenceDTO matchReferenceDTO : matchlistDTO.getMatches()) {
            partidaService.salvarOuEditar(invocador, matchReferenceDTO);
        }
    }

    public void update(Invocador invocador) throws Exception {
        MatchlistDTO matchlistDTO = riotApiService.getSummonerMatchlist(invocador.getAccountId(), invocador.getRegion());

        for (MatchReferenceDTO matchReferenceDTO : matchlistDTO.getMatches()) {
            partidaService.salvarOuEditar(invocador, matchReferenceDTO);
        }
    }

    public void delete(Invocador invocador) {
        List<Partida> partidas = partidaService.findAllBySummoner(invocador);

        for (Partida partida : partidas) {
            partidaService.excluir(partida.getId());
        }
    }
}
