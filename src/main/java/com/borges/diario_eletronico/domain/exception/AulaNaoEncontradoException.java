package com.borges.diario_eletronico.domain.exception;

public class AulaNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public AulaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public AulaNaoEncontradoException(Long alunoId) {
		this(String.format("Não existe um cadastro da aula com código %d", alunoId));
	}

}
