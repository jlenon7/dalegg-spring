package com.demomvc.curso.boot.service;

import java.util.List;

import com.demomvc.curso.boot.dto.MatchDTO;
import com.demomvc.curso.boot.dto.MatchlistDTO;
import com.demomvc.curso.boot.dto.SummonerDTO;
import com.demomvc.curso.boot.dto.TierDTO;

public interface RiotApiService {
    SummonerDTO getSummoner(String name, String region) throws Exception;
    MatchDTO getSummonerMatch(String matchId, String region) throws Exception;
    List<TierDTO> getSummonerTier(String summonerId, String region) throws Exception;
    MatchlistDTO getSummonerMatchlist(String accountId, String region) throws Exception;
}
