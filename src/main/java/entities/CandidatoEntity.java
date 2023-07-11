package entities;

import utils.StatusCandidato;


public class CandidatoEntity {
	private String nome;
	private StatusCandidato status;

	public CandidatoEntity(String nome, StatusCandidato status) {
		this.nome = nome;
		this.status = status;
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
