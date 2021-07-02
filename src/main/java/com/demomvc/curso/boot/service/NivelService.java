package com.demomvc.curso.boot.service;

import com.demomvc.curso.boot.domain.Invocador;
import com.demomvc.curso.boot.domain.Nivel;
import com.demomvc.curso.boot.dto.TierDTO;
import com.demomvc.curso.boot.util.PaginacaoUtil;

import java.util.List;

public interface NivelService {
    void salvarOuEditar(Invocador invocador, TierDTO tierDto);

    void excluir(Long id);

    Nivel buscarPorId(Long id);

    void editar(Long id, TierDTO tierDto);

    List<Nivel> buscarTodos();

    PaginacaoUtil<Nivel> buscarPorPagina(Invocador invocador, int pagina, String direcao);

    List<Nivel> findAllBySummoner(Invocador invocador);
}
