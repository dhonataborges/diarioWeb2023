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

import com.borges.diario_eletronico.domain.model.SerieNivelSubnivel;
import com.borges.diario_eletronico.domain.repository.SerieNivelSubnivelRepository;
import com.borges.diario_eletronico.domain.service.SerieNivelSubnivelService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/series")
public class SerieNivelSubnivelController {
	
	@Autowired
	private SerieNivelSubnivelRepository serieRepository;
	
	@Autowired
	private SerieNivelSubnivelService serieNivelSubnivelService;
	
	@GetMapping
	public List<SerieNivelSubnivel> listar() {
		return serieRepository.findAll();
	}

	@GetMapping("/{serieId}")
	public SerieNivelSubnivel buscar(@PathVariable Long serieId) {
		return serieNivelSubnivelService.buscarOuFalhar(serieId);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public SerieNivelSubnivel adicionar(@Valid @RequestBody SerieNivelSubnivel serie) {
		return serieNivelSubnivelService.salvar(serie);
	}

	@PutMapping("/{serieId}")
	public SerieNivelSubnivel atualizar(@PathVariable Long serieId, @Valid @RequestBody SerieNivelSubnivel serie) {
		SerieNivelSubnivel serieAtual = serieNivelSubnivelService.buscarOuFalhar(serieId);

		BeanUtils.copyProperties(serie, serieAtual, "id");

		return serieNivelSubnivelService.salvar(serieAtual);
	}
//
//	@DeleteMapping("/{serieId}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void remover(@PathVariable Long serieId) {
//		serieNivelSubnivelService.excluir(serieId);
//	}
		
}