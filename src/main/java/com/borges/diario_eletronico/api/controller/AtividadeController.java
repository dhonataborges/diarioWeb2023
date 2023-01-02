package com.borges.diario_eletronico.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.borges.diario_eletronico.domain.model.Atividade;
import com.borges.diario_eletronico.domain.repository.AtividadeRepository;
import com.borges.diario_eletronico.domain.service.AtividadeService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/atividades")
public class AtividadeController {
	
	@Autowired
	private AtividadeService atividadeService;
	
	@Autowired
	private AtividadeRepository atividadeRepository;
	
	@GetMapping
	public List<Atividade> listar() {
		return atividadeRepository.findAll();
	}

	@GetMapping("/{atividadeId}")
	public Atividade buscar(@PathVariable Long atividadeId) {
		return atividadeService.buscarOuFalhar(atividadeId);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Atividade adicionar(@Valid @RequestBody Atividade atividade) {
		return atividadeService.salvar(atividade);
	}

	@PutMapping("/{atividadeId}")
	public Atividade atualizar(@PathVariable Long atividadeId, @Valid @RequestBody Atividade atividade) {
		Atividade atividadeAtual = atividadeService.buscarOuFalhar(atividadeId);

		BeanUtils.copyProperties(atividade, atividadeAtual, "id");

		return atividadeService.salvar(atividadeAtual);
	}

	@DeleteMapping("/{atividadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long atividadeId) {
		atividadeService.excluir(atividadeId);
	}
		
}