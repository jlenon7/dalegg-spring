package com.demomvc.curso.boot.service;

import java.time.LocalDate;
import java.util.List;

import com.demomvc.curso.boot.dao.InvocadorDao;
import com.demomvc.curso.boot.dto.SummonerDTO;
import com.demomvc.curso.boot.publishers.EventManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demomvc.curso.boot.domain.Invocador;
import com.demomvc.curso.boot.util.PaginacaoUtil;

@Service
@Transactional(readOnly = false)
public class InvocadorServiceImpl implements InvocadorService {
	@Autowired
	private EventManager eventManager;

	@Autowired
	private InvocadorDao invocadorDao;

	@Autowired
	private RiotApiService riotApiService;

	public boolean verifyUniqueSummoner(String name, String region) {
		Invocador invocador = invocadorDao.findByNameAndRegion(name, region, false);

		return invocador != null;
	}

	@Override
	public Invocador salvar(Invocador invocador) throws Exception {
		if (this.verifyUniqueSummoner(invocador.getName(), invocador.getRegion())) {
			return null;
		}

		SummonerDTO summonerDto = riotApiService.getSummoner(invocador.getName(), invocador.getRegion());

		invocador.setName(summonerDto.getName());
		invocador.setPuuid(summonerDto.getPuuid());
		invocador.setSummonerId(summonerDto.getId());
		invocador.setAccountId(summonerDto.getAccountId());
		invocador.setLevel(summonerDto.getSummonerLevel());
		invocador.setProfileIcon(summonerDto.getProfileIconId());
		invocador.setRevisionDate(summonerDto.getRevisionDate());

		Invocador verifyDeleted = invocadorDao.findByNameAndRegion(invocador.getName(), invocador.getRegion(), true);

		if (verifyDeleted == null) {
			invocadorDao.save(invocador);
		} else {
			verifyDeleted.setDeletedAt(null);
			verifyDeleted.setName(summonerDto.getName());
			verifyDeleted.setLevel(summonerDto.getSummonerLevel());
			verifyDeleted.setProfileIcon(summonerDto.getProfileIconId());
			verifyDeleted.setRevisionDate(summonerDto.getRevisionDate());

			invocadorDao.update(verifyDeleted);
		}

		Invocador summoner = invocadorDao.findByNameAndRegion(invocador.getName(), invocador.getRegion(), false);

		eventManager.notify("createSummoner", summoner);

		return summoner;
	}

	@Override
	public void editar(Long id) throws Exception {
		Invocador invocador = invocadorDao.findById(id);
		SummonerDTO summonerDto = riotApiService.getSummoner(invocador.getName(), invocador.getRegion());

		invocador.setName(summonerDto.getName());
		invocador.setPuuid(summonerDto.getPuuid());
		invocador.setSummonerId(summonerDto.getId());
		invocador.setLevel(summonerDto.getSummonerLevel());
		invocador.setAccountId(summonerDto.getAccountId());
		invocador.setProfileIcon(summonerDto.getProfileIconId());
		invocador.setRevisionDate(summonerDto.getRevisionDate());

		invocadorDao.update(invocador);

		Invocador summoner = invocadorDao.findByNameAndRegion(invocador.getName(), invocador.getRegion(), false);

		eventManager.notify("updateSummoner", summoner);
	}

	@Override
	public void excluir(Long id) throws Exception {
		Invocador invocador = invocadorDao.findById(id);

		invocador.setDeletedAt(LocalDate.now());
		invocadorDao.update(invocador);

		eventManager.notify("deleteSummoner", invocador);
	}

	@Override
	@Transactional(readOnly = true)
	public Invocador buscarPorId(Long id) {
		return invocadorDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Invocador> buscarTodos() {
		return invocadorDao.findAll();
	}
	
	@Override
	public PaginacaoUtil<Invocador> buscarPorPagina(int pagina, String direcao) {
		return invocadorDao.buscaPaginada(pagina, direcao);
	}
}
