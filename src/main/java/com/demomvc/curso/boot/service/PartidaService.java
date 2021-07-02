package com.demomvc.curso.boot.service;

import com.demomvc.curso.boot.domain.Invocador;
import com.demomvc.curso.boot.domain.Partida;
import com.demomvc.curso.boot.dto.MatchReferenceDTO;
import com.demomvc.curso.boot.util.PaginacaoUtil;

import java.util.List;

public interface PartidaService {
    void salvarOuEditar(Invocador invocador, MatchReferenceDTO matchReferenceDTO);

    void excluir(Long id);

    Partida buscarPorId(Long id);

    void editar(Long id, MatchReferenceDTO matchReferenceDTO);

    List<Partida> buscarTodos();

    PaginacaoUtil<Partida> buscarPorPagina(Invocador invocador, int pagina, String direcao);

    List<Partida> findAllBySummoner(Invocador invocador);
}
