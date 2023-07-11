package com.segundo.segundoteste.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.segundo.segundoteste.entities.CandidatoEntity;
import com.segundo.segundoteste.exception.CandidatoException;
import com.segundo.segundoteste.service.ProcessoSeletivoService;
import com.segundo.segundoteste.utils.StatusCandidato;

@RestController
@RequestMapping("/api/v1/hiring")
public class ProcessoSeletivoController {
	private ProcessoSeletivoService service;

	public ProcessoSeletivoController() {
		this.service = new ProcessoSeletivoService();
	}

	@PostMapping("/start")
	public int iniciarProcesso(@RequestBody CandidatoEntity candidato) {
		return service.iniciarProcesso(candidato.getNome());
	}

	@PostMapping("/schedule")
	public void marcarEntrevista(@RequestBody CandidatoEntity candidato) throws CandidatoException {
		service.marcarEntrevista(candidato.getCodCandidato());
	}

	@PostMapping("/disqualify")
	public void desqualificarCandidato(@RequestBody CandidatoEntity candidato) throws CandidatoException {
		service.desqualificarCandidato(candidato.getCodCandidato());

	}

	@PostMapping("/approve")
	public void aprovarCandidato(@RequestBody CandidatoEntity candidato) throws CandidatoException {
		service.aprovarCandidato(candidato.getCodCandidato());
	}

	@GetMapping("/status/candidate/{codCandidato}")
	public StatusCandidato verificarStatusCandidato(@PathVariable int codCandidato) throws CandidatoException {
			return service.verificarStatusCandidato(codCandidato);

	}

	@GetMapping("/approved")
	public List<String> obterAprovados() {
		return service.obterAprovados();
	}
}
