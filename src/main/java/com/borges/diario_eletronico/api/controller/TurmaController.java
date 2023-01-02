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

import com.borges.diario_eletronico.domain.model.Turma;
import com.borges.diario_eletronico.domain.repository.TurmaRepository;
import com.borges.diario_eletronico.domain.service.TurmaService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/turmas")
public class TurmaController {
	
	@Autowired
	private TurmaService turmaService;
	
	@Autowired
	private TurmaRepository turmaRepository;
	
	@GetMapping
	public List<Turma> listar() {
		return turmaRepository.findAll();
	}

	@GetMapping("/{turmaId}")
	public Turma buscar(@PathVariable Long turmaId) {
		return turmaService.buscarOuFalhar(turmaId);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Turma adicionar(@Valid @RequestBody Turma turma) {
		return turmaService.salvar(turma);
	}

	@PutMapping("/{turmaId}")
	public Turma atualizar(@PathVariable Long turmaId, @Valid @RequestBody Turma turma) {
		Turma turmaAtual = turmaService.buscarOuFalhar(turmaId);

		BeanUtils.copyProperties(turma, turmaAtual, "id");

		return turmaService.salvar(turmaAtual);
	}

	@DeleteMapping("/{turmaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long turmaId) {
		turmaService.excluir(turmaId);
	}
		
}