package com.borges.diario_eletronico.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.borges.diario_eletronico.domain.exception.AlunoAulaNaoEncontradoException;
import com.borges.diario_eletronico.domain.exception.AlunoNaoEncontradoException;
import com.borges.diario_eletronico.domain.exception.EntidadeEmUsoException;
import com.borges.diario_eletronico.domain.model.Aluno;
import com.borges.diario_eletronico.domain.model.AlunoAula;
import com.borges.diario_eletronico.domain.model.Aula;
import com.borges.diario_eletronico.domain.repository.AlunoAulaRepository;

@Service
public class AlunoAulaService {

	private static final String MSG_ALUNO_AULA_EM_USO = "AlunoAula de código %d não pode ser removido, pois está em uso.";
	
	@Autowired
	private AlunoAulaRepository alunoAulaRepository;
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private AulaService aulaService;
	
	public AlunoAula salvar(AlunoAula alunoAula) {

		Long alunoId = alunoAula.getAluno().getId();
		Aluno aluno = alunoService.buscarOuFalhar(alunoId);
		
		Long aulaId = alunoAula.getAula().getId();
		Aula aula = aulaService.buscarOuFalhar(aulaId);
		
		alunoAula.setAluno(aluno);
		alunoAula.setAula(aula);

		return alunoAulaRepository.save(alunoAula);
	}

	public void excluir(Long alunoAulaId) {
		try {
			alunoAulaRepository.deleteById(alunoAulaId);

		} catch (EmptyResultDataAccessException e) {
			throw new AlunoNaoEncontradoException(alunoAulaId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ALUNO_AULA_EM_USO, alunoAulaId));
		}
	}

	public AlunoAula buscarOuFalhar(Long alunoAulaId) {
		return alunoAulaRepository.findById(alunoAulaId).orElseThrow(() -> new AlunoAulaNaoEncontradoException(alunoAulaId));
	}

}