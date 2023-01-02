package com.borges.diario_eletronico.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.borges.diario_eletronico.domain.exception.AlunoAtividadeNaoEncontradoException;
import com.borges.diario_eletronico.domain.exception.AlunoNaoEncontradoException;
import com.borges.diario_eletronico.domain.exception.EntidadeEmUsoException;
import com.borges.diario_eletronico.domain.exception.NotaMaiorException;
import com.borges.diario_eletronico.domain.model.Aluno;
import com.borges.diario_eletronico.domain.model.AlunoAtividade;
import com.borges.diario_eletronico.domain.model.Atividade;
import com.borges.diario_eletronico.domain.repository.AlunoAtividadeRepository;

@Service
public class AlunoAtividadeService {

	private static final String MSG_ALUNO_ATIVIDADE_EM_USO = "AlunoAtividade de código %d não pode ser removido, pois está em uso.";

	@Autowired
	private AlunoAtividadeRepository alunoAtividadeRepository;

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private AtividadeService atividadeService;

	public AlunoAtividade salvar(AlunoAtividade alunoAtividade) {
						
		Double n1 = alunoAtividade.getAtividade().getNotaMaxima();
		Double n2 = alunoAtividade.getNota();

		if (n2 > n1) {
			throw new NotaMaiorException("Nota " + alunoAtividade.getNota() + " informada é maior que o valor defindo!");
			
		}
		
		Long alunoId = alunoAtividade.getAluno().getId();
		Aluno aluno = alunoService.buscarOuFalhar(alunoId);
		
		Long atividadeId = alunoAtividade.getAtividade().getId();
		Atividade atividade = atividadeService.buscarOuFalhar(atividadeId);
		
		alunoAtividade.setAluno(aluno);
		alunoAtividade.setAtividade(atividade);;
		

		return alunoAtividadeRepository.save(alunoAtividade);
	}

	public void excluir(Long alunoAtividadeId) {
		try {
			alunoAtividadeRepository.deleteById(alunoAtividadeId);

		} catch (EmptyResultDataAccessException e) {
			throw new AlunoNaoEncontradoException(alunoAtividadeId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ALUNO_ATIVIDADE_EM_USO, alunoAtividadeId));
		}
	}

	public AlunoAtividade buscarOuFalhar(Long alunoAtividadeId) {
		return alunoAtividadeRepository.findById(alunoAtividadeId)
				.orElseThrow(() -> new AlunoAtividadeNaoEncontradoException(alunoAtividadeId));
	}

}




















