package entities;

public class CandidatoEntity {
	private String nome;
	private String status;

	public CandidatoEntity(String nome, String status) {
		this.nome = nome;
		this.status = status;
	}

	public String getNome() {
		return nome;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
