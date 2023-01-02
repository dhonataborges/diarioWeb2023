package com.borges.diario_eletronico.domain.exception;

public class TurmaNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public TurmaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public TurmaNaoEncontradoException(Long turmaId) {
		this(String.format("Não existe um cadastro de turma com código %d", turmaId));
	}

}
