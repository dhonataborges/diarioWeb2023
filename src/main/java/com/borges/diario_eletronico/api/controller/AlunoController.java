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

import com.borges.diario_eletronico.domain.model.Aluno;
import com.borges.diario_eletronico.domain.repository.AlunoRepository;
import com.borges.diario_eletronico.domain.service.AlunoService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@GetMapping
	public List<Aluno> listar() {
		return alunoRepository.findAll();
	}

	@GetMapping("/{alunoId}")
	public Aluno buscar(@PathVariable Long alunoId) {
		return alunoService.buscarOuFalhar(alunoId);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Aluno adicionar(@Valid @RequestBody Aluno aluno) {
		return alunoService.salvar(aluno);
	}

	@PutMapping("/{alunoId}")
	public Aluno atualizar(@PathVariable Long alunoId, @Valid @RequestBody Aluno aluno) {
		Aluno alunoAtual = alunoService.buscarOuFalhar(alunoId);

		BeanUtils.copyProperties(aluno, alunoAtual, "id");

		return alunoService.salvar(alunoAtual);
	}

	@DeleteMapping("/{alunoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long alunoId) {
		alunoService.excluir(alunoId);
	}
		
}