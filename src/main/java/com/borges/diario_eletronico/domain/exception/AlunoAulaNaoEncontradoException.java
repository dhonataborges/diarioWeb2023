package com.borges.diario_eletronico.domain.exception;

public class AlunoAulaNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public AlunoAulaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public AlunoAulaNaoEncontradoException(Long alunoAulaId) {
		this(String.format("Não existe um cadastro de alunoAula com código %d", alunoAulaId));
	}

}
