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

import com.borges.diario_eletronico.domain.model.Matricula;
import com.borges.diario_eletronico.domain.repository.MatriculaRepository;
import com.borges.diario_eletronico.domain.service.MatriculaService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/matriculas")
public class MatriculaController {
	
	@Autowired
	private MatriculaService matriculaService;
	
	@Autowired
	private MatriculaRepository matriculaRepository;
	
	@GetMapping
	public List<Matricula> listar() {
		return matriculaRepository.findAll();
	}

	@GetMapping("/{matriculaId}")
	public Matricula buscar(@PathVariable Long matriculaId) {
		return matriculaService.buscarOuFalhar(matriculaId);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Matricula adicionar(@Valid @RequestBody Matricula matricula) {
		return matriculaService.salvar(matricula);
	}

	@PutMapping("/{matriculaId}")
	public Matricula atualizar(@PathVariable Long matriculaId, @Valid @RequestBody Matricula matricula) {
		Matricula matriculaAtual = matriculaService.buscarOuFalhar(matriculaId);

		BeanUtils.copyProperties(matricula, matriculaAtual, "id");

		return matriculaService.salvar(matriculaAtual);
	}

	@DeleteMapping("/{matriculaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long matriculaId) {
		matriculaService.excluir(matriculaId);
	}
		
}