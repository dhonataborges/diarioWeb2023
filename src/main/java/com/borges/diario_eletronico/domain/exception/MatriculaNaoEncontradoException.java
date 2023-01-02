package com.borges.diario_eletronico.domain.exception;

public class MatriculaNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public MatriculaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public MatriculaNaoEncontradoException(Long matriculaId) {
		this(String.format("Não existe um cadastro de matricula com código %d", matriculaId));
	}

}
