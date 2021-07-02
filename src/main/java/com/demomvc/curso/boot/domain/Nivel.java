package com.demomvc.curso.boot.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@SuppressWarnings("serial")
@Entity
@Table(name = "niveis")
public class Nivel extends AbstractEntity<Long> {
    @NotBlank
    @Column(name = "league_id", nullable = false, unique = false)
    private String leagueId;

    @NotBlank
    @Column(name = "summoner_id", nullable = false, unique = false)
    private String summonerId;

    @NotBlank
    @Column(name = "summoner_name", nullable = false, unique = false)
    private String summonerName;

    @NotBlank
    @Column(name = "queue_type", nullable = false, unique = false)
    private String queueType;

    @NotBlank
    @Column(nullable = false, unique = false)
    private String tier;

    @NotBlank
    @Column(nullable = false, unique = false)
    private String rank;

    @Column(name = "league_points", nullable = false, unique = false)
    private Integer leaguePoints;

    @Column(nullable = false, unique = false)
    private Integer wins;

    @Column(nullable = false, unique = false)
    private Integer losses;

    @Column(name = "hot_streak", nullable = false, unique = false)
    private Boolean hotStreak;

    @Column(nullable = false, unique = false)
    private Boolean veteran;

    @Column(name = "fresh_blood", nullable = false, unique = false)
    private Boolean freshBlood;

    @Column(nullable = false, unique = false)
    private Boolean inactive;

    @DateTimeFormat(iso = ISO.DATE)
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "DATE")
    private LocalDate createdAt;

    @DateTimeFormat(iso = ISO.DATE)
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "DATE")
    private LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "invocador_id", nullable = false)
    private Invocador invocador;

    /*
     * Getters and Setters
     */

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(Integer leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Boolean getHotStreak() {
        return hotStreak;
    }

    public void setHotStreak(Boolean hotStreak) {
        this.hotStreak = hotStreak;
    }

    public Boolean getVeteran() {
        return veteran;
    }

    public void setVeteran(Boolean veteran) {
        this.veteran = veteran;
    }

    public Boolean getFreshBlood() {
        return freshBlood;
    }

    public void setFreshBlood(Boolean freshBlood) {
        this.freshBlood = freshBlood;
    }

    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Invocador getInvocador() {
        return invocador;
    }

    public void setInvocador(Invocador invocador) {
        this.invocador = invocador;
    }
}
