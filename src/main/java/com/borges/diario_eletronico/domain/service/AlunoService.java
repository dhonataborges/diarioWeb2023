package com.borges.diario_eletronico.domain.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.borges.diario_eletronico.domain.exception.AlunoNaoEncontradoException;
import com.borges.diario_eletronico.domain.exception.EntidadeEmUsoException;
import com.borges.diario_eletronico.domain.model.Aluno;
import com.borges.diario_eletronico.domain.repository.AlunoRepository;

@Service
public class AlunoService {

	private static final String MSG_ALUNO_EM_USO = "Aluno de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private AlunoRepository alunoRepository;

//	@Autowired
//	private TurmaService turmaService;
//	
//	@Autowired
//	private SerieNivelSubnivelService serieService;
//	
//	@Autowired
//	private DisciplinaService disciplinaService;

	public Aluno salvar(@Valid Aluno aluno) {
				
//		Long turmaId = aluno.getTurma().getId();
//		Turma turma = turmaService.buscarOuFalhar(turmaId);
//
//		Long serieId = aluno.getTurma().getSerieNivelSubnivel().getId();
//		SerieNivelSubnivel serie = serieService.buscarOuFalhar(serieId);
//
//		Long disciplinaId = aluno.getTurma().getSerieNivelSubnivel().getDisciplina().getId();
//		Disciplina disciplina = disciplinaService.buscarOuFalhar(disciplinaId);
//
//		serie.setDisciplina(disciplina);
//		turma.setSerieNivelSubnivel(serie);
//
//		aluno.setTurma(turma);
		
		return alunoRepository.save(aluno);
	}

	public void excluir(Long alunoId) {
		try {
			alunoRepository.deleteById(alunoId);

		} catch (EmptyResultDataAccessException e) {
			throw new AlunoNaoEncontradoException(alunoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ALUNO_EM_USO, alunoId));
		}
	}

	public Aluno buscarOuFalhar(Long alunoId) {
		return alunoRepository.findById(alunoId).orElseThrow(() -> new AlunoNaoEncontradoException(alunoId));
	}

}