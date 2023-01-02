package com.borges.diario_eletronico.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.borges.diario_eletronico.domain.exception.AlunoNaoEncontradoException;
import com.borges.diario_eletronico.domain.exception.AulaNaoEncontradoException;
import com.borges.diario_eletronico.domain.exception.EntidadeEmUsoException;
import com.borges.diario_eletronico.domain.model.Aula;
import com.borges.diario_eletronico.domain.repository.AulaRepository;

@Service
public class AulaService {

	private static final String MSG_ALUNO_EM_USO = "Aula de código %d não pode ser removido, pois está em uso.";
	
	@Autowired
	private AulaRepository aulaRepository;

//	@Autowired
//	private TurmaService turmaService;
//	
//	@Autowired
//	private SerieNivelSubnivelService serieService;
//	
//	@Autowired
//	private DisciplinaService disciplinaService;

	public Aula salvar(Aula aula) {

//		Long turmaId = aula.getTurma().getId();
//		Turma turma = turmaService.buscarOuFalhar(turmaId);
//		
//		Long serieId = aula.getTurma().getSerieNivelSubnivel().getId();
//		SerieNivelSubnivel serie = serieService.buscarOuFalhar(serieId);
//		
//		Long disciplinaId = aula.getTurma().getSerieNivelSubnivel().getDisciplina().getId();
//		Disciplina disciplina = disciplinaService.buscarOuFalhar(disciplinaId);
//		
//		serie.setDisciplina(disciplina);
//		turma.setSerieNivelSubnivel(serie);
//
//		aula.setTurma(turma);

		return aulaRepository.save(aula);
	}

	public void excluir(Long aulaId) {
		try {
			aulaRepository.deleteById(aulaId);

		} catch (EmptyResultDataAccessException e) {
			throw new AlunoNaoEncontradoException(aulaId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ALUNO_EM_USO, aulaId));
		}
	}

	public Aula buscarOuFalhar(Long aulaId) {
		return aulaRepository.findById(aulaId).orElseThrow(() -> new AulaNaoEncontradoException(aulaId));
	}

}