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

import com.borges.diario_eletronico.domain.model.Aula;
import com.borges.diario_eletronico.domain.repository.AulaRepository;
import com.borges.diario_eletronico.domain.service.AulaService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/aulas")
public class AulaController {
	
	@Autowired
	private AulaService aulaService;
	
	@Autowired
	private AulaRepository aulaRepository;
	
	@GetMapping
	public List<Aula> listar() {
		return aulaRepository.findAll();
	}

	@GetMapping("/{aulaId}")
	public Aula buscar(@PathVariable Long aulaId) {
		return aulaService.buscarOuFalhar(aulaId);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Aula adicionar(@Valid @RequestBody Aula aula) {
		return aulaService.salvar(aula);
	}

	@PutMapping("/{aulaId}")
	public Aula atualizar(@PathVariable Long aulaId, @Valid @RequestBody Aula aula) {
		Aula aulaAtual = aulaService.buscarOuFalhar(aulaId);

		BeanUtils.copyProperties(aula, aulaAtual, "id");

		return aulaService.salvar(aulaAtual);
	}

	@DeleteMapping("/{aulaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long aulaId) {
		aulaService.excluir(aulaId);
	}
		
}