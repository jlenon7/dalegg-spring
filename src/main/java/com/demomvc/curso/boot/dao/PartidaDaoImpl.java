package com.demomvc.curso.boot.dao;

import java.util.List;

import com.demomvc.curso.boot.domain.Invocador;
import com.demomvc.curso.boot.domain.Partida;
import org.springframework.stereotype.Repository;
import com.demomvc.curso.boot.util.PaginacaoUtil;

import javax.persistence.TypedQuery;

@Repository
public class PartidaDaoImpl extends AbstractDao<Partida, Long> implements PartidaDao {
    public PaginacaoUtil<Partida> buscaPaginada(Invocador invocador, int pagina, String direcao) {
        int tamanho = 10;
        int inicio = (pagina - 1) * tamanho;

        List<Partida> partidas = getEntityManager()
                .createQuery("select s from Partida s where s.invocador = :invocador order by s.timestamp " + direcao, Partida.class)
                .setParameter("invocador", invocador)
                .setFirstResult(inicio)
                .setMaxResults(tamanho)
                .getResultList();

        long totalRegistros = count();
        long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;

        return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, direcao, partidas);
    }

    public List<Partida> findAllBySummoner(Invocador invocador) {
        return getEntityManager()
                .createQuery("select p from Partida p where p.invocador = :invocador", Partida.class)
                .setParameter("invocador", invocador)
                .getResultList();
    }

    public long count() {
        return getEntityManager()
                .createQuery("select count(*) from Partida", Long.class)
                .getSingleResult();
    }

    public Partida findByGameId(Invocador invocador, Long gameId) {
        TypedQuery<Partida> consulta = getEntityManager()
                .createQuery("select p from Partida p where p.invocador = :invocador and p.gameId = :gameId", Partida.class)
                .setParameter("invocador", invocador)
                .setParameter("gameId", gameId);

        List<Partida> partidas = consulta.getResultList();

        if (partidas.isEmpty()) return null;

        return partidas.get(0);
    }
}
