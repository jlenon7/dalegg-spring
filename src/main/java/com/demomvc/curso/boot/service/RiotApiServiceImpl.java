package com.demomvc.curso.boot.service;

import com.demomvc.curso.boot.dto.MatchDTO;
import com.demomvc.curso.boot.dto.MatchlistDTO;
import com.demomvc.curso.boot.dto.SummonerDTO;
import com.demomvc.curso.boot.dto.TierDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class RiotApiServiceImpl implements RiotApiService {
    @Autowired
    private RestTemplate restTemplate;

    private String apiKey = "your-api-key-here";

    private String buildUri(String region, String endpoint) throws Exception {
        if (this.apiKey.equals("your-api-key-here")) {
            throw new Exception("Please provide your Riot API Key inside RiotApiServiceImpl");
        }

        return "https://" + region + ".api.riotgames.com/lol" + endpoint + "?api_key=" + this.apiKey;
    }

    public SummonerDTO getSummoner(String name, String region) throws Exception {
       ResponseEntity<SummonerDTO> response = restTemplate.exchange(
               this.buildUri(region, "/summoner/v4/summoners/by-name/" + name),
               HttpMethod.GET,
               null,
               new ParameterizedTypeReference<SummonerDTO>() {}
       );

        return response.getBody();
    }

    public List<TierDTO> getSummonerTier(String summonerId, String region) throws Exception {
        ResponseEntity<List<TierDTO>> response = restTemplate.exchange(
                this.buildUri(region, "/league/v4/entries/by-summoner/" + summonerId),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TierDTO>>() {}
         );

        return response.getBody();
    }

    public MatchDTO getSummonerMatch(String matchId, String region) throws Exception {
        ResponseEntity<MatchDTO> response = restTemplate.exchange(
                this.buildUri(region, "/match/v4/matches/" + matchId),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<MatchDTO>() {}
        );

        return response.getBody();
    }

    public MatchlistDTO getSummonerMatchlist(String accountId, String region) throws Exception {
        ResponseEntity<MatchlistDTO> response = restTemplate.exchange(
                this.buildUri(region, "/match/v4/matchlists/by-account/" + accountId),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<MatchlistDTO>() {}
        );

        return response.getBody();
    }
}
