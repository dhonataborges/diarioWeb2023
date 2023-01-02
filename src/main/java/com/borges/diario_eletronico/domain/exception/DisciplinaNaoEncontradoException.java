package com.borges.diario_eletronico.domain.exception;

public class DisciplinaNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public DisciplinaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public DisciplinaNaoEncontradoException(Long disciplinaId) {
		this(String.format("Não existe um cadastro de disciplina com código %d", disciplinaId));
	}

}
