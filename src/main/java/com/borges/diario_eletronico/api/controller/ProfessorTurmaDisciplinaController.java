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

import com.borges.diario_eletronico.domain.model.ProfessorTurmaDisciplina;
import com.borges.diario_eletronico.domain.repository.ProfessorTurmaDisciplinaRepository;
import com.borges.diario_eletronico.domain.service.ProfessorTurmaDisciplinaService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/professorTurmaDisciplina")
public class ProfessorTurmaDisciplinaController {
	
	@Autowired
	private ProfessorTurmaDisciplinaService professorTurmaDisciplinaService;
	
	@Autowired
	private ProfessorTurmaDisciplinaRepository professorTurmaDisciplinaRepository;
	
	@GetMapping
	public List<ProfessorTurmaDisciplina> listar() {
		return professorTurmaDisciplinaRepository.findAll();
	}

	@GetMapping("/{professorTurmaDisciplinaId}")
	public ProfessorTurmaDisciplina buscar(@PathVariable Long professorTurmaDisciplinaId) {
		return professorTurmaDisciplinaService.buscarOuFalhar(professorTurmaDisciplinaId);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ProfessorTurmaDisciplina adicionar(@Valid @RequestBody ProfessorTurmaDisciplina professorTurmaDisciplina) {
		return professorTurmaDisciplinaService.salvar(professorTurmaDisciplina);
	}

	@PutMapping("/{professorTurmaDisciplinaId}")
	public ProfessorTurmaDisciplina atualizar(@PathVariable Long professorTurmaDisciplinaId, @Valid @RequestBody ProfessorTurmaDisciplina professorTurmaDisciplina) {
		ProfessorTurmaDisciplina professorTurmaDisciplinaAtual = professorTurmaDisciplinaService.buscarOuFalhar(professorTurmaDisciplinaId);

		BeanUtils.copyProperties(professorTurmaDisciplina, professorTurmaDisciplinaAtual, "id");

		return professorTurmaDisciplinaService.salvar(professorTurmaDisciplinaAtual);
	}

	@DeleteMapping("/{professorTurmaDisciplinaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long professorTurmaDisciplinaId) {
		professorTurmaDisciplinaService.excluir(professorTurmaDisciplinaId);
	}
		
}