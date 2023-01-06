package com.borges.diario_eletronico.domain.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.borges.diario_eletronico.domain.exception.EntidadeEmUsoException;
import com.borges.diario_eletronico.domain.exception.ProfessorTurmaNaoEncontradoException;
import com.borges.diario_eletronico.domain.model.Professor;
import com.borges.diario_eletronico.domain.model.ProfessorTurma;
import com.borges.diario_eletronico.domain.model.Turma;
import com.borges.diario_eletronico.domain.repository.ProfessorTurmaRepository;

@Service
public class ProfessorTurmaService {

	private static final String MSG_PROFESSOR_TURMA_EM_USO = "ProfessorTurma de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private ProfessorTurmaRepository professorTurmaRepository;

	@Autowired
	private TurmaService turmaService;
	
	@Autowired
	private ProfessorService professorService;


	public ProfessorTurma salvar(@Valid ProfessorTurma professorTurma) {
				
		Long turmaId = professorTurma.getTurma().getId();
		Turma turma = turmaService.buscarOuFalhar(turmaId);

		Long professorId = professorTurma.getProfessor().getId();
		Professor professor = professorService.buscarOuFalhar(professorId);
		
		professorTurma.setTurma(turma);
		professorTurma.setProfessor(professor);
		
		return professorTurmaRepository.save(professorTurma);
	}

	public void excluir(Long professorTurmaId) {
		try {
			professorTurmaRepository.deleteById(professorTurmaId);

		} catch (EmptyResultDataAccessException e) {
			throw new ProfessorTurmaNaoEncontradoException(professorTurmaId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_PROFESSOR_TURMA_EM_USO, professorTurmaId));
		}
	}

	public ProfessorTurma buscarOuFalhar(Long professorTurmaId) {
		return professorTurmaRepository.findById(professorTurmaId).orElseThrow(() -> new ProfessorTurmaNaoEncontradoException(professorTurmaId));
	}

}