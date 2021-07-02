package com.demomvc.curso.boot.service;

import java.time.LocalDate;
import java.util.List;

import com.demomvc.curso.boot.dao.NivelDao;
import com.demomvc.curso.boot.domain.Invocador;
import com.demomvc.curso.boot.domain.Nivel;
import com.demomvc.curso.boot.dto.TierDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.demomvc.curso.boot.util.PaginacaoUtil;

@Service
@Transactional(readOnly = false)
public class NivelServiceImpl implements NivelService {
    @Autowired
    private NivelDao nivelDao;

    @Autowired
    private RiotApiServiceImpl riotApiService;

    @Override
    public void excluir(Long id) {
        nivelDao.delete(id);
    }

    public void editar(Long id, TierDTO tierDto) {
        Nivel nivel = this.buscarPorId(id);

        nivel.setTier(tierDto.getTier());
        nivel.setRank(tierDto.getRank());
        nivel.setWins(tierDto.getWins());
        nivel.setLosses(tierDto.getLosses());
        nivel.setVeteran(tierDto.getVeteran());
        nivel.setLeagueId(tierDto.getLeagueId());
        nivel.setInactive(tierDto.getInactive());
        nivel.setHotStreak(tierDto.getHotStreak());
        nivel.setQueueType(tierDto.getQueueType());
        nivel.setSummonerId(tierDto.getSummonerId());
        nivel.setFreshBlood(tierDto.getFreshBlood());
        nivel.setLeaguePoints(tierDto.getLeaguePoints());
        nivel.setSummonerName(tierDto.getSummonerName());

        nivelDao.update(nivel);
    }

    @Override
    public void salvarOuEditar(Invocador invocador, TierDTO tierDto) {
        Nivel nivelRepetido = nivelDao.findByQueueType(invocador, tierDto.getQueueType());

        if (nivelRepetido == null) {
            Nivel nivel = new Nivel();

            nivel.setInvocador(invocador);
            nivel.setTier(tierDto.getTier());
            nivel.setRank(tierDto.getRank());
            nivel.setWins(tierDto.getWins());
            nivel.setLosses(tierDto.getLosses());
            nivel.setVeteran(tierDto.getVeteran());
            nivel.setLeagueId(tierDto.getLeagueId());
            nivel.setInactive(tierDto.getInactive());
            nivel.setHotStreak(tierDto.getHotStreak());
            nivel.setQueueType(tierDto.getQueueType());
            nivel.setSummonerId(tierDto.getSummonerId());
            nivel.setFreshBlood(tierDto.getFreshBlood());
            nivel.setLeaguePoints(tierDto.getLeaguePoints());
            nivel.setSummonerName(tierDto.getSummonerName());

            nivelDao.save(nivel);
        } else {
            this.editar(nivelRepetido.getId(), tierDto);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Nivel buscarPorId(Long id) {
        return nivelDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Nivel> buscarTodos() {
        return nivelDao.findAll();
    }

    @Override
    public PaginacaoUtil<Nivel> buscarPorPagina(Invocador invocador, int pagina, String direcao) {
        return nivelDao.buscaPaginada(invocador, pagina, direcao);
    }

    public List<Nivel> findAllBySummoner(Invocador invocador) {
        return nivelDao.findAllBySummoner(invocador);
    }
}
