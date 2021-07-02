package com.demomvc.curso.boot.dao;

import com.demomvc.curso.boot.domain.Invocador;
import com.demomvc.curso.boot.domain.Partida;
import com.demomvc.curso.boot.util.PaginacaoUtil;

import java.util.List;

public interface PartidaDao {
    void save(Partida partida);

    void update(Partida partida);

    void delete(Long id);

    Partida findById(Long id);

    List<Partida> findAll();

    PaginacaoUtil<Partida> buscaPaginada(Invocador invocador, int pagina, String direcao);

    List<Partida> findAllBySummoner(Invocador invocador);

    Partida findByGameId(Invocador invocador, Long gameId);
}
