package com.demomvc.curso.boot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demomvc.curso.boot.domain.Invocador;
import com.demomvc.curso.boot.util.PaginacaoUtil;

import javax.persistence.TypedQuery;

@Repository
public class InvocadorDaoImpl extends AbstractDao<Invocador, Long> implements InvocadorDao {
	public PaginacaoUtil<Invocador> buscaPaginada(int pagina, String direcao) {
		int tamanho = 10;
		int inicio = (pagina - 1) * tamanho;

		List<Invocador> invocadores = getEntityManager()
				.createQuery("select s from Invocador s where s.deletedAt = null order by s.name " + direcao, Invocador.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		long totalRegistros = count();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;
		
		return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, direcao, invocadores);
	}
	
	public long count() {
		return getEntityManager()
				.createQuery("select count(*) from Invocador", Long.class)
				.getSingleResult();
	}

	public Invocador findByNameAndRegion(String name, String region, Boolean deleted) {
		TypedQuery<Invocador> consulta;

		if (deleted) {
			consulta = getEntityManager()
					.createQuery("select i from Invocador i where i.name = :name and i.region = :region", Invocador.class)
					.setParameter("name", name)
					.setParameter("region", region);
		} else {
			consulta = getEntityManager()
					.createQuery("select i from Invocador i where i.name = :name and i.region = :region and i.deletedAt = null", Invocador.class)
					.setParameter("name", name)
					.setParameter("region", region);
		}

		List<Invocador> invocadores = consulta.getResultList();

		if (invocadores.isEmpty()) return null;

		return invocadores.get(0);
	}
}
