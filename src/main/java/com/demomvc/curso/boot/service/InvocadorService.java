package com.demomvc.curso.boot.service;

import java.util.List;

import com.demomvc.curso.boot.domain.Invocador;
import com.demomvc.curso.boot.util.PaginacaoUtil;

public interface InvocadorService {

	Invocador salvar(Invocador invocador) throws Exception;
	
	void editar(Long id) throws Exception;
	
	void excluir(Long id) throws Exception;
	
	Invocador buscarPorId(Long id);
	
	List<Invocador> buscarTodos();
	
	PaginacaoUtil<Invocador> buscarPorPagina(int pagina, String direcao);
}
