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

import com.borges.diario_eletronico.domain.model.ProfessorTurma;
import com.borges.diario_eletronico.domain.repository.ProfessorTurmaRepository;
import com.borges.diario_eletronico.domain.service.ProfessorTurmaService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/professorTurma")
public class ProfessorTurmaController {
	
	@Autowired
	private ProfessorTurmaService professorTurmaService;
	
	@Autowired
	private ProfessorTurmaRepository professorTurmaRepository;
	
	@GetMapping
	public List<ProfessorTurma> listar() {
		return professorTurmaRepository.findAll();
	}

	@GetMapping("/{professorTurmaId}")
	public ProfessorTurma buscar(@PathVariable Long professorTurmaId) {
		return professorTurmaService.buscarOuFalhar(professorTurmaId);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ProfessorTurma adicionar(@Valid @RequestBody ProfessorTurma professorTurma) {
		return professorTurmaService.salvar(professorTurma);
	}

	@PutMapping("/{professorTurmaId}")
	public ProfessorTurma atualizar(@PathVariable Long professorTurmaId, @Valid @RequestBody ProfessorTurma professorTurma) {
		ProfessorTurma professorTurmaAtual = professorTurmaService.buscarOuFalhar(professorTurmaId);

		BeanUtils.copyProperties(professorTurma, professorTurmaAtual, "id");

		return professorTurmaService.salvar(professorTurmaAtual);
	}

	@DeleteMapping("/{professorTurmaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long professorTurmaId) {
		professorTurmaService.excluir(professorTurmaId);
	}
		
}