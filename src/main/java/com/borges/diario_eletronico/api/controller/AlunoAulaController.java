package com.borges.diario_eletronico.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.borges.diario_eletronico.domain.model.AlunoAula;
import com.borges.diario_eletronico.domain.repository.AlunoAulaRepository;
import com.borges.diario_eletronico.domain.service.AlunoAulaService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/alunoAulas")
public class AlunoAulaController {
	
	@Autowired
	private AlunoAulaService alunoAulaService;
	
	@Autowired
	private AlunoAulaRepository alunoAulaRepository;
		
	@GetMapping
	public List<AlunoAula> listar() {
		return alunoAulaRepository.findAll();
	}

	@GetMapping("/{alunoAulaId}")
	public AlunoAula buscar(@PathVariable Long alunoAulaId) {
		return alunoAulaService.buscarOuFalhar(alunoAulaId);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public AlunoAula adicionar(@Valid @RequestBody AlunoAula alunoAula) {
		return alunoAulaService.salvar(alunoAula);
	}

	@PutMapping("/{alunoAulaId}")
	public AlunoAula atualizar(@PathVariable Long alunoAulaId, @Valid @RequestBody AlunoAula alunoAula) {
		AlunoAula alunoAulaAtual = alunoAulaService.buscarOuFalhar(alunoAulaId);

		BeanUtils.copyProperties(alunoAula, alunoAulaAtual, "id");

		return alunoAulaService.salvar(alunoAulaAtual);
	}
	
	@PatchMapping("/{alunoAulaId}")
	public AlunoAula atualizarParcial(@PathVariable Long alunoAulaId,
			@RequestBody Map<String, Object> campos, HttpServletRequest request) {
		
		AlunoAula alunoAulaAtual = alunoAulaService.buscarOuFalhar(alunoAulaId);
		
		merge(campos, alunoAulaAtual, request);
		
		return atualizar(alunoAulaId, alunoAulaAtual);
	}
	
	private void merge(Map<String, Object> dadosOrigem, AlunoAula alunoAulaDestino,
			HttpServletRequest request) {
		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
			
			AlunoAula alunoAulaOrigem = objectMapper.convertValue(dadosOrigem, AlunoAula.class);
			
			dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
				Field field = ReflectionUtils.findField(AlunoAula.class, nomePropriedade);
				field.setAccessible(true);
			
				Object novoValor = ReflectionUtils.getField(field, alunoAulaOrigem);
				
				ReflectionUtils.setField(field, alunoAulaDestino, novoValor);
			});
			
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
		}
		
	}
	
	@DeleteMapping("/{alunoAulaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long alunoAulaId) {
		alunoAulaService.excluir(alunoAulaId);
	}
		
}