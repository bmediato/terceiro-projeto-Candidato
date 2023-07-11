package controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entities.CandidatoEntity;
import exception.CandidatoException;
import service.ProcessoSeletivoService;
import utils.StatusCandidato;

@RestController
@RequestMapping(value="/api/v1/hiring")
public class ProcessoSeletivoController {
	private ProcessoSeletivoService service;
	
	public ProcessoSeletivoController() {
		this.service = new ProcessoSeletivoService();
	}
	
	@PostMapping("/start")
	public ResponseEntity<Integer> iniciarProcesso(@RequestBody CandidatoEntity candidato) {
		int codCandidato = service.iniciarProcesso(candidato.getNome());
		return ResponseEntity.ok(codCandidato);
	}
	
	@PostMapping("/schedule")
    public void marcarEntrevista(@RequestBody CandidatoEntity candidato) {
		try {
			service.marcarEntrevista(candidato.getCodCandidato());
		} catch(CandidatoException e) {
			ResponseEntity.badRequest().body(e.getMessage());
		}
	}

    @PostMapping("/disqualify")
    public void desqualificarCandidato(@RequestBody CandidatoEntity candidato) {
        try {
            service.desqualificarCandidato(candidato.getCodCandidato());
        } catch (CandidatoException e) {
            ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/approve")
    public void aprovarCandidato(@RequestBody CandidatoEntity candidato) {
        try {
            service.aprovarCandidato(candidato.getCodCandidato());
        } catch (CandidatoException e) {
            ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/status/candidate/{codCandidato}")
    public ResponseEntity<StatusCandidato> verificarStatusCandidato(@PathVariable int codCandidato) {
        try {
           StatusCandidato status = service.verificarStatusCandidato(codCandidato);
           return ResponseEntity.ok(status);
        } catch (CandidatoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/approved")
    public ResponseEntity<List<String>> obterAprovados() {
        List<String> aprovados = service.obterAprovados();
        return ResponseEntity.ok(aprovados);
    }
}
