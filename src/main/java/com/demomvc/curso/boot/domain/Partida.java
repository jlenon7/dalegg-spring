package com.demomvc.curso.boot.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@SuppressWarnings("serial")
@Entity
@Table(name = "partidas")
public class Partida extends AbstractEntity<Long> {
    @Column(name = "game_id", nullable = false, unique = false)
    private Long gameId;

    @Column(nullable = false, unique = false)
    private String role;

    @Column(name = "platform_id", nullable = false, unique = false)
    private String platformId;

    @Column(nullable = false, unique = false)
    private Integer champion;

    @Column(nullable = false, unique = false)
    private Integer queue;

    @Column(nullable = false, unique = false)
    private String lane;

    @Column(nullable = false, unique = false)
    private Long timestamp;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "DATE")
    private LocalDate createdAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "DATE")
    private LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "invocador_id", nullable = false)
    private Invocador invocador;

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public Integer getChampion() {
        return champion;
    }

    public void setChampion(Integer champion) {
        this.champion = champion;
    }

    public Integer getQueue() {
        return queue;
    }

    public void setQueue(Integer queue) {
        this.queue = queue;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
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
