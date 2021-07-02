package com.demomvc.curso.boot.web.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.demomvc.curso.boot.dao.NivelDaoImpl;
import com.demomvc.curso.boot.dao.PartidaDaoImpl;
import com.demomvc.curso.boot.domain.Nivel;
import com.demomvc.curso.boot.domain.Partida;
import com.demomvc.curso.boot.service.NivelService;
import com.demomvc.curso.boot.service.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demomvc.curso.boot.domain.Invocador;
import com.demomvc.curso.boot.service.InvocadorService;
import com.demomvc.curso.boot.util.PaginacaoUtil;

@Controller
@RequestMapping("/invocador")
public class InvocadorController {
	@Autowired
	private InvocadorService invocadorService;

	@Autowired
	private NivelService nivelService;

	@Autowired
	private PartidaService partidaService;

	@GetMapping("/cadastrar")
	public String cadastrar(Invocador invocador) {
		return "invocador/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model, 
						 @RequestParam("page") Optional<Integer> page, 
						 @RequestParam("dir") Optional<String> dir) {
		
		int paginaAtual = page.orElse(1);
		String ordem = dir.orElse("asc");		
		
		PaginacaoUtil<Invocador> pageInvocador = invocadorService.buscarPorPagina(paginaAtual, ordem);
		
		model.addAttribute("pageInvocador", pageInvocador);
		return "invocador/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Invocador invocador, BindingResult result, RedirectAttributes attr) throws Exception {
		
		if (result.hasErrors()) {
			return "invocador/cadastro";
		}
		
		Invocador summoner = invocadorService.salvar(invocador);

		if (summoner == null) {
			attr.addFlashAttribute("fail", "O invocador " + invocador.getName() + " já existe na região " + invocador.getRegion());

			return "redirect:/invocador/cadastrar";
		}

		attr.addFlashAttribute("success", "Invocador inserido com sucesso.");
		return "redirect:/invocador/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Long id, RedirectAttributes attr) throws Exception {
		invocadorService.editar(id);
		attr.addFlashAttribute("success", "Invocador editado com sucesso.");
		return "redirect:/invocador/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) throws Exception {
		invocadorService.excluir(id);
		attr.addFlashAttribute("success", "Invocador removido com sucesso.");
		return "redirect:/invocador/listar";
	}

	@GetMapping("/listar/niveis/{id}")
	public String listarNiveis(@PathVariable("id") Long id,
							   ModelMap model,
						 @RequestParam("page") Optional<Integer> page,
						 @RequestParam("dir") Optional<String> dir, RedirectAttributes attr) {

		int paginaAtual = page.orElse(1);
		String ordem = dir.orElse("asc");

		Invocador invocador = invocadorService.buscarPorId(id);

		if (invocador == null) {
			attr.addFlashAttribute("fail", "Invocador não encontrado!");

			return "redirect:/invocador/listar";
		}

		PaginacaoUtil<Nivel> pageNivel = nivelService.buscarPorPagina(invocador, paginaAtual, ordem);

		if (pageNivel.getRegistros().isEmpty()) {
			attr.addFlashAttribute("fail", "O invocador " + invocador.getName() + " não possui nenhum nivel cadastrado");

			return "redirect:/invocador/listar";
		}

		model.addAttribute("pageNivel", pageNivel);
		return "nivel/lista";
	}

	@GetMapping("/listar/partidas/{id}")
	public String listarPartidas(@PathVariable("id") Long id,
							   ModelMap model,
							   @RequestParam("page") Optional<Integer> page,
							   @RequestParam("dir") Optional<String> dir, RedirectAttributes attr) {

		int paginaAtual = page.orElse(1);
		String ordem = dir.orElse("desc");

		Invocador invocador = invocadorService.buscarPorId(id);

		if (invocador == null) {
			attr.addFlashAttribute("fail", "Invocador não encontrado!");

			return "redirect:/invocador/listar";
		}

		PaginacaoUtil<Partida> pagePartida = partidaService.buscarPorPagina(invocador, paginaAtual, ordem);

		if (pagePartida.getRegistros().isEmpty()) {
			attr.addFlashAttribute("fail", "O invocador " + invocador.getName() + " não possui nenhuma partida cadastrada");

			return "redirect:/invocador/listar";
		}

		model.addAttribute("pagePartida", pagePartida);
		return "partida/lista";
	}
}
