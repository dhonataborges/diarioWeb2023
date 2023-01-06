package com.borges.diario_eletronico.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borges.diario_eletronico.domain.exception.SerieNivelSubnivelNaoEncontradoException;
import com.borges.diario_eletronico.domain.model.SerieNivelSubnivel;
import com.borges.diario_eletronico.domain.repository.SerieNivelSubnivelRepository;

@Service
public class SerieNivelSubnivelService {

//	private static final String MSG_ALUNO_EM_USO = "Serie de código %d não pode ser removido, pois está em uso";

	@Autowired
	private SerieNivelSubnivelRepository serieRepository;
	
	public SerieNivelSubnivel salvar(SerieNivelSubnivel serie) {
		
		return serieRepository.save(serie);

	}

//	public void excluir(Long turmaId) {
//		try {
//			serieRepository.deleteById(turmaId);
//
//		} catch (EmptyResultDataAccessException e) {
//			throw new TurmaNaoEncontradoException(turmaId);
//
//		} catch (DataIntegrityViolationException e) {
//			throw new EntidadeEmUsoException(String.format(MSG_ALUNO_EM_USO, turmaId));
//		}
//	}

	public SerieNivelSubnivel buscarOuFalhar(Long serieId) {
		return serieRepository.findById(serieId).orElseThrow(() -> new SerieNivelSubnivelNaoEncontradoException(serieId));
	}

}
