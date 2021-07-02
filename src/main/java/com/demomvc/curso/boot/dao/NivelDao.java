package com.demomvc.curso.boot.dao;

import com.demomvc.curso.boot.domain.Invocador;
import com.demomvc.curso.boot.domain.Nivel;
import com.demomvc.curso.boot.util.PaginacaoUtil;

import java.util.List;

public interface NivelDao {
    void save(Nivel nivel);

    void update(Nivel nivel);

    void delete(Long id);

    Nivel findById(Long id);

    List<Nivel> findAll();

    PaginacaoUtil<Nivel> buscaPaginada(Invocador invocador, int pagina, String direcao);

    Nivel findByQueueType(Invocador invocador, String queueType);

    List<Nivel> findAllBySummoner(Invocador invocador);
}
