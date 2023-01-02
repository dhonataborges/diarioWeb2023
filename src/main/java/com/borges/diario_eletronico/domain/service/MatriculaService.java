package com.borges.diario_eletronico.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.borges.diario_eletronico.domain.exception.EntidadeDuplicadaException;
import com.borges.diario_eletronico.domain.exception.EntidadeEmUsoException;
import com.borges.diario_eletronico.domain.exception.MatriculaNaoEncontradoException;
import com.borges.diario_eletronico.domain.exception.TurmaNaoEncontradoException;
import com.borges.diario_eletronico.domain.model.Aluno;
import com.borges.diario_eletronico.domain.model.Matricula;
import com.borges.diario_eletronico.domain.model.Turma;
import com.borges.diario_eletronico.domain.repository.MatriculaRepository;

@Service
public class MatriculaService {

	private static final String MSG_MATRICULA_EM_USO = "Matricula de código %d não pode ser removido, pois está em uso";

	@Autowired
	private MatriculaRepository matriculaRepository;
		
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private TurmaService turmaService;
	
	public Matricula salvar(Matricula matricula) {
		
		if (findbyMatricula(matricula) != null) {

			throw new EntidadeDuplicadaException("Matricula " + matricula.getCodMat() + " já cadastrada no sistema!");

		}
		
		Long alunoId = matricula.getAluno().getId();
						
		Aluno aluno = alunoService.buscarOuFalhar(alunoId);
		
		Long matriculaId = matricula.getTurma().getId();
		
		Turma turma = turmaService.buscarOuFalhar(matriculaId);
		
		matricula.setAluno(aluno);
		
		matricula.setTurma(turma);
		
		return matriculaRepository.save(matricula);
		
	}

	public void excluir(Long matriculaId) {
		try {
			matriculaRepository.deleteById(matriculaId);

		} catch (EmptyResultDataAccessException e) {
			throw new TurmaNaoEncontradoException(matriculaId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_MATRICULA_EM_USO, matriculaId));
		}
	}

	public Matricula buscarOuFalhar(Long matriculaId) {
		return matriculaRepository.findById(matriculaId).orElseThrow(() -> new MatriculaNaoEncontradoException(matriculaId));
	}
	
	private Matricula findbyMatricula(Matricula matricula) {
	Matricula obj = matriculaRepository.findByMatricula(matricula.getCodMat());

	if (obj != null) {
		return obj;
	}
	return null;
}
	
}