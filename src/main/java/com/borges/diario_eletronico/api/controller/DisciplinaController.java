package com.borges.diario_eletronico.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.borges.diario_eletronico.domain.model.Disciplina;
import com.borges.diario_eletronico.domain.repository.DisciplinaRepository;
import com.borges.diario_eletronico.domain.service.DisciplinaService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/disciplinas")
public class DisciplinaController {
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@GetMapping
	public List<Disciplina> listar() {
		return disciplinaRepository.findAll();
	}

	@GetMapping("/{disciplinaId}")
	public Disciplina buscar(@PathVariable Long disciplinaId) {
		return disciplinaService.buscarOuFalhar(disciplinaId);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Disciplina adicionar(@Valid @RequestBody Disciplina disciplina) {
		return disciplinaService.salvar(disciplina);
	}

	@PutMapping("/{disciplinaId}")
	public Disciplina atualizar(@PathVariable Long disciplinaId, @Valid @RequestBody Disciplina disciplina) {
		Disciplina disciplinaAtual = disciplinaService.buscarOuFalhar(disciplinaId);

		BeanUtils.copyProperties(disciplina, disciplinaAtual, "id");

		return disciplinaService.salvar(disciplinaAtual);
	}
//
//	@DeleteMapping("/{turmaId}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void remover(@PathVariable Long turmaId) {
//		turmaService.excluir(turmaId);
//	}
		
}