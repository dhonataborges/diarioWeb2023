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

import com.borges.diario_eletronico.domain.model.AlunoAtividade;
import com.borges.diario_eletronico.domain.repository.AlunoAtividadeRepository;
import com.borges.diario_eletronico.domain.service.AlunoAtividadeService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/alunoAtividades")
public class AlunoAtividadeController {
	
	@Autowired
	private AlunoAtividadeService alunoAtividadeService;
	
	@Autowired
	private AlunoAtividadeRepository alunoAtividadeRepository;
		
	@GetMapping
	public List<AlunoAtividade> listar() {
		return alunoAtividadeRepository.findAll();
	}

	@GetMapping("/{alunoAtividadeId}")
	public AlunoAtividade buscar(@PathVariable Long alunoAtividadeId) {
		return alunoAtividadeService.buscarOuFalhar(alunoAtividadeId);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public AlunoAtividade adicionar(@Valid @RequestBody AlunoAtividade alunoAtividade) {
		return alunoAtividadeService.salvar(alunoAtividade);
	}

	@PutMapping("/{alunoAtividadeId}")
	public AlunoAtividade atualizar(@PathVariable Long alunoAtividadeId, @Valid @RequestBody AlunoAtividade alunoAula) {
		AlunoAtividade alunoAulaAtual = alunoAtividadeService.buscarOuFalhar(alunoAtividadeId);

		BeanUtils.copyProperties(alunoAula, alunoAulaAtual, "id");

		return alunoAtividadeService.salvar(alunoAulaAtual);
	}
	
	@PatchMapping("/{alunoAtividadeId}")
	public AlunoAtividade atualizarParcial(@PathVariable Long alunoAtividadeId,
			@RequestBody Map<String, Object> campos, HttpServletRequest request) {
		
		AlunoAtividade alunoAulaAtual = alunoAtividadeService.buscarOuFalhar(alunoAtividadeId);
		
		merge(campos, alunoAulaAtual, request);
		
		return atualizar(alunoAtividadeId, alunoAulaAtual);
	}
	
	private void merge(Map<String, Object> dadosOrigem, AlunoAtividade alunoAulaDestino,
			HttpServletRequest request) {
		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
			
			AlunoAtividade alunoAulaOrigem = objectMapper.convertValue(dadosOrigem, AlunoAtividade.class);
			
			dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
				Field field = ReflectionUtils.findField(AlunoAtividade.class, nomePropriedade);
				field.setAccessible(true);
			
				Object novoValor = ReflectionUtils.getField(field, alunoAulaOrigem);
				
				ReflectionUtils.setField(field, alunoAulaDestino, novoValor);
			});
			
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
		}
		
	}
	
	@DeleteMapping("/{alunoAtividadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long alunoAtividadeId) {
		alunoAtividadeService.excluir(alunoAtividadeId);
	}
		
}