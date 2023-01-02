package com.borges.diario_eletronico.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.borges.diario_eletronico.domain.exception.DisciplinaNaoEncontradoException;
import com.borges.diario_eletronico.domain.exception.EntidadeEmUsoException;
import com.borges.diario_eletronico.domain.exception.TurmaNaoEncontradoException;
import com.borges.diario_eletronico.domain.model.Disciplina;
import com.borges.diario_eletronico.domain.repository.DisciplinaRepository;

@Service
public class DisciplinaService {

	private static final String MSG_DISCIPLINA_EM_USO = "Disciplina de código %d não pode ser removido, pois está em uso";

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	public Disciplina salvar(Disciplina disciplina) {

		return disciplinaRepository.save(disciplina);
	}

	public void excluir(Long disciplinaId) {
		try {
			disciplinaRepository.deleteById(disciplinaId);

		} catch (EmptyResultDataAccessException e) {
			throw new TurmaNaoEncontradoException(disciplinaId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_DISCIPLINA_EM_USO, disciplinaId));
		}
	}

	public Disciplina buscarOuFalhar(Long disciplinaId) {
		return disciplinaRepository.findById(disciplinaId).orElseThrow(() -> new DisciplinaNaoEncontradoException(disciplinaId));
	}
}