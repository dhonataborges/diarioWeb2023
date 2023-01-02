package com.borges.diario_eletronico.domain.exception;

public class AtividadeNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public AtividadeNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public AtividadeNaoEncontradoException(Long atividadeId) {
		this(String.format("Não existe um cadastro de atividade com código %d", atividadeId));
	}

}
