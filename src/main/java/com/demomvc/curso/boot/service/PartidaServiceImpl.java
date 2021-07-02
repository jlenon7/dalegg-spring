package com.demomvc.curso.boot.service;

import java.time.LocalDate;
import java.util.List;

import com.demomvc.curso.boot.domain.Invocador;
import com.demomvc.curso.boot.domain.Nivel;
import com.demomvc.curso.boot.domain.Partida;
import com.demomvc.curso.boot.dto.MatchReferenceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demomvc.curso.boot.dao.PartidaDao;
import com.demomvc.curso.boot.util.PaginacaoUtil;

@Service
@Transactional(readOnly = false)
public class PartidaServiceImpl implements PartidaService {
    @Autowired
    private PartidaDao partidaDao;

    @Autowired
    private RiotApiServiceImpl riotApiService;

    @Override
    public void excluir(Long id) {
        partidaDao.delete(id);
    }

    public void editar(Long id, MatchReferenceDTO matchReferenceDTO) {
        Partida partida = this.buscarPorId(id);

        partida.setLane(matchReferenceDTO.getLane());
        partida.setRole(matchReferenceDTO.getRole());
        partida.setQueue(matchReferenceDTO.getQueue());
        partida.setGameId(matchReferenceDTO.getGameId());
        partida.setChampion(matchReferenceDTO.getChampion());
        partida.setTimestamp(matchReferenceDTO.getTimestamp());
        partida.setPlatformId(matchReferenceDTO.getPlatformId());

        partidaDao.update(partida);
    }

    @Override
    public void salvarOuEditar(Invocador invocador, MatchReferenceDTO matchReferenceDTO) {
        Partida partidaRepetida = partidaDao.findByGameId(invocador, matchReferenceDTO.getGameId());

        if (partidaRepetida == null) {
            Partida partida = new Partida();

            partida.setInvocador(invocador);
            partida.setLane(matchReferenceDTO.getLane());
            partida.setRole(matchReferenceDTO.getRole());
            partida.setQueue(matchReferenceDTO.getQueue());
            partida.setGameId(matchReferenceDTO.getGameId());
            partida.setChampion(matchReferenceDTO.getChampion());
            partida.setTimestamp(matchReferenceDTO.getTimestamp());
            partida.setPlatformId(matchReferenceDTO.getPlatformId());

            partidaDao.save(partida);
        } else {
            this.editar(partidaRepetida.getId(), matchReferenceDTO);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Partida buscarPorId(Long id) {
        return partidaDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partida> buscarTodos() {
        return partidaDao.findAll();
    }

    @Override
    public PaginacaoUtil<Partida> buscarPorPagina(Invocador invocador, int pagina, String direcao) {
        return partidaDao.buscaPaginada(invocador, pagina, direcao);
    }

    public List<Partida> findAllBySummoner(Invocador invocador) {
        return partidaDao.findAllBySummoner(invocador);
    }
}
