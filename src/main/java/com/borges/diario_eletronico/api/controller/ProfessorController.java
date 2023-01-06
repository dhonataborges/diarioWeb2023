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

import com.borges.diario_eletronico.domain.model.Professor;
import com.borges.diario_eletronico.domain.repository.ProfessorRepository;
import com.borges.diario_eletronico.domain.service.ProfessorService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/professor")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@GetMapping
	public List<Professor> listar() {
		return professorRepository.findAll();
	}

	@GetMapping("/{alunoId}")
	public Professor buscar(@PathVariable Long alunoId) {
		return professorService.buscarOuFalhar(alunoId);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Professor adicionar(@Valid @RequestBody Professor aluno) {
		return professorService.salvar(aluno);
	}

	@PutMapping("/{alunoId}")
	public Professor atualizar(@PathVariable Long alunoId, @Valid @RequestBody Professor aluno) {
		Professor alunoAtual = professorService.buscarOuFalhar(alunoId);

		BeanUtils.copyProperties(aluno, alunoAtual, "id");

		return professorService.salvar(alunoAtual);
	}

	@DeleteMapping("/{alunoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long alunoId) {
		professorService.excluir(alunoId);
	}
		
}