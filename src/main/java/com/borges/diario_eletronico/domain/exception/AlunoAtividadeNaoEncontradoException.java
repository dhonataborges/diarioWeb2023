package com.borges.diario_eletronico.domain.exception;

public class AlunoAtividadeNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public AlunoAtividadeNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public AlunoAtividadeNaoEncontradoException(Long alunoAulaId) {
		this(String.format("Não existe um cadastro de alunoAtividade com código %d", alunoAulaId));
	}

}
