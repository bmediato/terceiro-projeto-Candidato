package segundoteste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.CandidatoEntity;
import exception.CandidatoException;

public class Segundo extends Exception{
	private Map<Integer, CandidatoEntity> candidatos;
	private int contador;

	public Segundo() {
		candidatos = new HashMap<Integer, CandidatoEntity>();
		contador = 1;
	}

	public int iniciarProcesso(String nome) {
		int codCandidato = contador++;
		CandidatoEntity candidato = new CandidatoEntity(nome, "Recebido");
		candidatos.put(codCandidato, candidato);
		return codCandidato;
	}

	public void marcarEntrevista(int codCandidato) throws CandidatoException {
		CandidatoException.verificarCandidatoExistente(candidatos, codCandidato);
		CandidatoEntity candidato = candidatos.get(codCandidato);
		if (candidato.getStatus().equals("Qualificado") || candidato.getStatus().equals("Aprovado")) {
			throw new CandidatoException("Candidato já participa do processo");
		} else {
			candidato.setStatus("Qualificado");
		}
	}

	public void desqualificarCandidato(int codCandidato) throws CandidatoException {
		CandidatoException.verificarCandidatoExistente(candidatos, codCandidato);
		candidatos.remove(codCandidato);
	}

	public String verificarStatusCandidato(int codCandidato) throws CandidatoException {
		CandidatoException.verificarCandidatoExistente(candidatos, codCandidato);
		CandidatoEntity candidato = candidatos.get(codCandidato);
		return candidato.getStatus();
	}

	public void aprovarCandidato(int codCandidato) throws CandidatoException {
		CandidatoException.verificarCandidatoExistente(candidatos, codCandidato);
		CandidatoEntity candidato = candidatos.get(codCandidato);
		if (candidato.getStatus().equals("Aprovado") || candidato.getStatus().equals("Recebido")) {
			throw new CandidatoException("Candidato já participa do processo");
		} else {
			candidato.setStatus("Aprovado");
		}
	}

	public List<String> obterAprovados() {
		List<String> aprovados = new ArrayList<>();

		for (CandidatoEntity candidato : candidatos.values()) {

			if (candidato.getStatus().equals("Aprovado")) {
				aprovados.add(candidato.getNome());
			}
		}
		return aprovados;
	}
}
