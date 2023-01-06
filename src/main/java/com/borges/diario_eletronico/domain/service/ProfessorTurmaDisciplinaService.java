package com.borges.diario_eletronico.domain.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.borges.diario_eletronico.domain.exception.EntidadeEmUsoException;
import com.borges.diario_eletronico.domain.exception.ProfessorTurmaDisciplinaNaoEncontradoException;
import com.borges.diario_eletronico.domain.model.Disciplina;
import com.borges.diario_eletronico.domain.model.ProfessorTurma;
import com.borges.diario_eletronico.domain.model.ProfessorTurmaDisciplina;
import com.borges.diario_eletronico.domain.repository.ProfessorTurmaDisciplinaRepository;

@Service
public class ProfessorTurmaDisciplinaService {

	private static final String MSG_PROFESSOR_TURMA_DISCIPLINA_EM_USO = "ProfessorTurmaDisciplina de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private ProfessorTurmaDisciplinaRepository professorTurmaDisciplinaRepository;

	@Autowired
	private ProfessorTurmaService professorTurmaService;
	
	@Autowired
	private DisciplinaService disciplinaService;


	public ProfessorTurmaDisciplina salvar(@Valid ProfessorTurmaDisciplina professorTurmaDisciplina) {
				
		Long professorTurmaId = professorTurmaDisciplina.getProfessorTurma().getId();
		ProfessorTurma professorTurma = professorTurmaService.buscarOuFalhar(professorTurmaId);

		Long disciplinaId = professorTurmaDisciplina.getDisciplina().getId();
		Disciplina disciplina = disciplinaService.buscarOuFalhar(disciplinaId);
		
		professorTurmaDisciplina.setProfessorTurma(professorTurma);;
		professorTurmaDisciplina.setDisciplina(disciplina);;
		
		return professorTurmaDisciplinaRepository.save(professorTurmaDisciplina);
	}

	public void excluir(Long professorTurmaDisciplinaId) {
		try {
			professorTurmaDisciplinaRepository.deleteById(professorTurmaDisciplinaId);

		} catch (EmptyResultDataAccessException e) {
			throw new ProfessorTurmaDisciplinaNaoEncontradoException(professorTurmaDisciplinaId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_PROFESSOR_TURMA_DISCIPLINA_EM_USO, professorTurmaDisciplinaId));
		}
	}

	public ProfessorTurmaDisciplina buscarOuFalhar(Long professorTurmaDisciplinaId) {
		return professorTurmaDisciplinaRepository.findById(professorTurmaDisciplinaId).orElseThrow(() -> new ProfessorTurmaDisciplinaNaoEncontradoException(professorTurmaDisciplinaId));
	}

}