package com.borges.diario_eletronico.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.borges.diario_eletronico.domain.exception.EntidadeEmUsoException;
import com.borges.diario_eletronico.domain.exception.TurmaNaoEncontradoException;
import com.borges.diario_eletronico.domain.model.Disciplina;
import com.borges.diario_eletronico.domain.model.SerieNivelSubnivel;
import com.borges.diario_eletronico.domain.model.Turma;
import com.borges.diario_eletronico.domain.repository.TurmaRepository;

@Service
public class TurmaService {

	private static final String MSG_ALUNO_EM_USO = "Turma de código %d não pode ser removido, pois está em uso";

	@Autowired
	private TurmaRepository turmaRepository;
		
	@Autowired
	private SerieNivelSubnivelService serieService;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	public Turma salvar(Turma turma) {
		
		Long serieId = turma.getSerieNivelSubnivel().getId();
						
		SerieNivelSubnivel serie = serieService.buscarOuFalhar(serieId);
		
//		Long disciplinaId = turma.getSerieNivelSubnivel().getDisciplina().getId();
//		
//		Disciplina disciplina = disciplinaService.buscarOuFalhar(disciplinaId);
//		
//		serie.setDisciplina(disciplina);;
		
		turma.setSerieNivelSubnivel(serie);
		
		return turmaRepository.save(turma);
	}

	public void excluir(Long turmaId) {
		try {
			turmaRepository.deleteById(turmaId);

		} catch (EmptyResultDataAccessException e) {
			throw new TurmaNaoEncontradoException(turmaId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ALUNO_EM_USO, turmaId));
		}
	}

	public Turma buscarOuFalhar(Long turmaId) {
		return turmaRepository.findById(turmaId).orElseThrow(() -> new TurmaNaoEncontradoException(turmaId));
	}
}