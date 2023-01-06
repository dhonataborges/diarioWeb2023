package com.borges.diario_eletronico.domain.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.borges.diario_eletronico.domain.exception.EntidadeEmUsoException;
import com.borges.diario_eletronico.domain.exception.NegocioException;
import com.borges.diario_eletronico.domain.exception.ProfessorNaoEncontradoException;
import com.borges.diario_eletronico.domain.model.Professor;
import com.borges.diario_eletronico.domain.repository.ProfessorRepository;

@Service
public class ProfessorService {

	private static final String MSG_PROFESSOR_EM_USO = "Professor de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private ProfessorRepository professorRepository;

	public Professor salvar(@Valid Professor professor) {
		
		if(verificaEmail(professor.getLogin()) != null) {
			throw new NegocioException("Email " +professor.getLogin()+ " já cadastrado!");
			
		}
		
		return professorRepository.save(professor);
	}

	public void excluir(Long professorId) {
		try {
			professorRepository.deleteById(professorId);

		} catch (EmptyResultDataAccessException e) {
			throw new ProfessorNaoEncontradoException(professorId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_PROFESSOR_EM_USO, professorId));
		}
	}

	public Professor buscarOuFalhar(Long professorId) {
		return professorRepository.findById(professorId).orElseThrow(() -> new ProfessorNaoEncontradoException(professorId));
	}
	
	public Professor verificaEmail(String emailAtual) {
		
		Professor email = professorRepository.findByLogin(emailAtual);
		
		if(email != null) {
			return email;
			
		}
		
		return null;
		
	}

}