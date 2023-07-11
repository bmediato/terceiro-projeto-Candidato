package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.CandidatoEntity;
import exception.CandidatoException;
import utils.StatusCandidato;

public class ProcessoSeletivoService {
	private Map<Integer, CandidatoEntity> candidatos;
	private int contador;

	public ProcessoSeletivoService() {
		candidatos = new HashMap<Integer, CandidatoEntity>();
		contador = 1;
	}

	public int iniciarProcesso(String nome) {
		int codCandidato = contador++;
		CandidatoEntity candidato = new CandidatoEntity(nome, StatusCandidato.RECEBIDO);
		candidatos.put(codCandidato, candidato);
		return codCandidato;
	}

	public void marcarEntrevista(int codCandidato) throws CandidatoException {
		CandidatoException.verificarCandidatoExistente(candidatos, codCandidato);
		CandidatoEntity candidato = candidatos.get(codCandidato);
		if (candidato.getStatus().equals(StatusCandidato.QUALIFICADO) || candidato.getStatus().equals(StatusCandidato.APROVADO)) {
			throw new CandidatoException("Candidato já participa do processo");
		} else {
			candidato.setStatus(StatusCandidato.QUALIFICADO);
		}
	}

	public void desqualificarCandidato(int codCandidato) throws CandidatoException {
		CandidatoException.verificarCandidatoExistente(candidatos, codCandidato);
		candidatos.remove(codCandidato);
	}

	public StatusCandidato verificarStatusCandidato(int codCandidato) throws CandidatoException {
		CandidatoException.verificarCandidatoExistente(candidatos, codCandidato);
		CandidatoEntity candidato = candidatos.get(codCandidato);
		return candidato.getStatus();
	}

	public void aprovarCandidato(int codCandidato) throws CandidatoException {
		CandidatoException.verificarCandidatoExistente(candidatos, codCandidato);
		CandidatoEntity candidato = candidatos.get(codCandidato);
		if (candidato.getStatus().equals(StatusCandidato.APROVADO) || candidato.getStatus().equals(StatusCandidato.RECEBIDO)) {
			throw new CandidatoException("Candidato já participa do processo");
		} else {
			candidato.setStatus(StatusCandidato.APROVADO);
		}
	}

	public List<String> obterAprovados() {
		List<String> aprovados = new ArrayList<>();

		for (CandidatoEntity candidato : candidatos.values()) {

			if (candidato.getStatus().equals(StatusCandidato.APROVADO)) {
				aprovados.add(candidato.getNome());
			}
		}
		return aprovados;
	}
}
