package controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entities.CandidatoEntity;
import service.ProcessoSeletivoService;

@RestController
@RequestMapping(value="/api/v1/hiring")
public class ProcessoSeletivoController {
	private ProcessoSeletivoService service;
	
	public ProcessoSeletivoController() {
		this.service = new ProcessoSeletivoService();
	}
	
	@PostMapping("/start")
	public int iniciarProcesso(@RequestBody CandidatoEntity candidato) {
		return service.iniciarProcesso(candidato.getNome());
	}
}
