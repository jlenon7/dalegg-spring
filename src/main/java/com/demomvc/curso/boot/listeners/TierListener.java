package com.demomvc.curso.boot.listeners;

import com.demomvc.curso.boot.domain.Nivel;
import com.demomvc.curso.boot.domain.Partida;
import com.demomvc.curso.boot.dto.TierDTO;
import com.demomvc.curso.boot.domain.Invocador;
import com.demomvc.curso.boot.service.NivelService;
import com.demomvc.curso.boot.service.RiotApiService;

import java.util.List;

public class TierListener implements EventListener {
    private NivelService nivelService;
    private RiotApiService riotApiService;

    public TierListener(NivelService nivelService, RiotApiService riotApiService) {
        this.nivelService = nivelService;
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
        List<TierDTO> tierListDto = riotApiService.getSummonerTier(invocador.getSummonerId(), invocador.getRegion());

        for (TierDTO tierDto : tierListDto) {
            nivelService.salvarOuEditar(invocador, tierDto);
        }
    }

    public void update(Invocador invocador) throws Exception {
        List<TierDTO> tierListDto = riotApiService.getSummonerTier(invocador.getSummonerId(), invocador.getRegion());

        for (TierDTO tierDto : tierListDto) {
            nivelService.salvarOuEditar(invocador, tierDto);
        }
    }

    public void delete(Invocador invocador) {
        List<Nivel> niveis = nivelService.findAllBySummoner(invocador);

        for (Nivel nivel : niveis) {
            nivelService.excluir(nivel.getId());
        }
    }
}
