package com.borges.diario_eletronico.domain.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.borges.diario_eletronico.domain.exception.AtividadeNaoEncontradoException;
import com.borges.diario_eletronico.domain.exception.EntidadeEmUsoException;
import com.borges.diario_eletronico.domain.model.Atividade;
import com.borges.diario_eletronico.domain.repository.AtividadeRepository;

@Service
public class AtividadeService {

	private static final String MSG_ATIVADADE_EM_USO = "Atividade de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private AtividadeRepository atividadeRepository;

//	@Autowired
//	private TurmaService turmaService;
//	
//	@Autowired
//	private SerieNivelSubnivelService serieService;
//	
//	@Autowired
//	private DisciplinaService disciplinaService;

	public Atividade salvar(@Valid Atividade atividade) {
				
//		Long turmaId = atividade.getTurma().getId();
//		Turma turma = turmaService.buscarOuFalhar(turmaId);
//
//		Long serieId = atividade.getTurma().getSerieNivelSubnivel().getId();
//		SerieNivelSubnivel serie = serieService.buscarOuFalhar(serieId);
//
//		Long disciplinaId = atividade.getTurma().getSerieNivelSubnivel().getDisciplina().getId();
//		Disciplina disciplina = disciplinaService.buscarOuFalhar(disciplinaId);
//
//		serie.setDisciplina(disciplina);
//		turma.setSerieNivelSubnivel(serie);
//
//		atividade.setTurma(turma);
		
		return atividadeRepository.save(atividade);
	}

	public void excluir(Long atividadeId) {
		try {
			atividadeRepository.deleteById(atividadeId);

		} catch (EmptyResultDataAccessException e) {
			throw new AtividadeNaoEncontradoException(atividadeId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ATIVADADE_EM_USO, atividadeId));
		}
	}

	public Atividade buscarOuFalhar(Long atividadeId) {
		return atividadeRepository.findById(atividadeId).orElseThrow(() -> new AtividadeNaoEncontradoException(atividadeId));
	}

}