package com.demomvc.curso.boot.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@SuppressWarnings("serial")
@Entity
@Table(name = "invocadores")
public class Invocador extends AbstractEntity<Long> {

	@NotBlank
	@Column(nullable = false, unique = false)
	private String name;

	@Column(nullable = false)
	private Long level;

	@NotBlank
	@Column(nullable = false)
	private String region;

	@Column(nullable = false, unique = true)
	private String puuid;

	@Column(name = "revision_date", nullable = false)
	private Long revisionDate;

	@Column(name = "profile_icon", nullable = false)
	private Integer profileIcon;

	@Column(name = "account_id", nullable = false, unique = true)
	private String accountId;

	@Column(name = "summoner_id", nullable = false, unique = true)
	private String summonerId;

	@DateTimeFormat(iso = ISO.DATE)
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, columnDefinition = "DATE")
	private LocalDate createdAt;

	@DateTimeFormat(iso = ISO.DATE)
	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false, columnDefinition = "DATE")
	private LocalDate updatedAt;

	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "deleted_at", columnDefinition = "DATE")
	private LocalDate deletedAt;

	@OneToMany(mappedBy = "invocador")
	private List<Nivel> niveis;

	@OneToMany(mappedBy = "invocador")
	private List<Partida> partidas;

	/*
	* Getters and Setters
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPuuid() {
		return puuid;
	}

	public void setPuuid(String puuid) {
		this.puuid = puuid;
	}

	public Long getRevisionDate() {
		return revisionDate;
	}

	public void setRevisionDate(Long revisionDate) {
		this.revisionDate = revisionDate;
	}

	public Integer getProfileIcon() {
		return profileIcon;
	}

	public void setProfileIcon(Integer profileIcon) {
		this.profileIcon = profileIcon;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getSummonerId() {
		return summonerId;
	}

	public void setSummonerId(String summonerId) {
		this.summonerId = summonerId;
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

	public LocalDate getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDate deletedAt) {
		this.deletedAt = deletedAt;
	}

	public List<Nivel> getNiveis() {
		return niveis;
	}

	public void setNiveis(List<Nivel> niveis) {
		this.niveis = niveis;
	}

	public List<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}
}
