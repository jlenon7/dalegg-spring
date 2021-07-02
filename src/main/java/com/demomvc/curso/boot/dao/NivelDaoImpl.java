package com.demomvc.curso.boot.dao;

import java.util.List;

import com.demomvc.curso.boot.domain.Invocador;
import com.demomvc.curso.boot.domain.Nivel;
import org.springframework.stereotype.Repository;
import com.demomvc.curso.boot.util.PaginacaoUtil;

import javax.persistence.TypedQuery;

@Repository
public class NivelDaoImpl extends AbstractDao<Nivel, Long> implements NivelDao {
    public PaginacaoUtil<Nivel> buscaPaginada(Invocador invocador, int pagina, String direcao) {
        int tamanho = 10;
        int inicio = (pagina - 1) * tamanho;

        List<Nivel> niveis = getEntityManager()
                .createQuery("select s from Nivel s where s.invocador = :invocador order by s.wins " + direcao, Nivel.class)
                .setParameter("invocador", invocador)
                .setFirstResult(inicio)
                .setMaxResults(tamanho)
                .getResultList();

        long totalRegistros = count();
        long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;

        return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, direcao, niveis);
    }

    public List<Nivel> findAllBySummoner(Invocador invocador) {
        return getEntityManager()
                .createQuery("select s from Nivel s where s.invocador = :invocador", Nivel.class)
                .setParameter("invocador", invocador)
                .getResultList();
    }

    public Nivel findByQueueType(Invocador invocador, String queueType) {
        TypedQuery<Nivel> consulta = getEntityManager()
                .createQuery("select s from Nivel s where s.invocador = :invocador and s.queueType = :queueType", Nivel.class)
                .setParameter("invocador", invocador)
                .setParameter("queueType", queueType);

        List<Nivel> niveis = consulta.getResultList();

        if (niveis.isEmpty()) return null;

        return niveis.get(0);
    }

    public long count() {
        return getEntityManager()
                .createQuery("select count(*) from Nivel", Long.class)
                .getSingleResult();
    }
}
