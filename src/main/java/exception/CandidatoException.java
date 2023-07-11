package exception;

import java.util.Map;

import entities.CandidatoEntity;

public class CandidatoException extends Exception {
	public CandidatoException(String message) {
		super(message);
	}

	public static void verificarCandidatoExistente(Map<Integer, CandidatoEntity> candidatos, int codCandidato)
			throws CandidatoException {
		if (!candidatos.containsKey(codCandidato)) {
			throw new CandidatoException("Candidato não encontrado");
		}
	}
}
