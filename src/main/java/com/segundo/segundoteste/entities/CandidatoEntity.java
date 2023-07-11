package com.segundo.segundoteste.entities;

import com.segundo.segundoteste.utils.StatusCandidato;

public class CandidatoEntity {
	private int codCandidato;
	private String nome;
	private StatusCandidato status;

	public CandidatoEntity() {

	}

	public CandidatoEntity(int codCandidato, String nome, StatusCandidato status) {
		this.codCandidato = codCandidato;
		this.nome = nome;
		this.status = status;
	}

	public int getCodCandidato() {
		return codCandidato;
	}

	public String getNome() {
		return nome;
	}

	public StatusCandidato getStatus() {
		return status;
	}

	public void setStatus(StatusCandidato status) {
		this.status = status;
	}

}
