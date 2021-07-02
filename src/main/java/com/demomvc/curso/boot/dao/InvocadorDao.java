package com.demomvc.curso.boot.dao;

import java.util.List;

import com.demomvc.curso.boot.domain.Invocador;
import com.demomvc.curso.boot.util.PaginacaoUtil;

public interface InvocadorDao {
	 	void save(Invocador invocador);

	    void update(Invocador invocador);

	    void delete(Long id);

	    Invocador findById(Long id);

	    List<Invocador> findAll();
	    
	   PaginacaoUtil<Invocador> buscaPaginada(int pagina, String direcao);

	   Invocador findByNameAndRegion(String name, String region, Boolean deleted);
}
