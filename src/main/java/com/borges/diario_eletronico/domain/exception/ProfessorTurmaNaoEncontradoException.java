package com.borges.diario_eletronico.domain.exception;

public class ProfessorTurmaNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public ProfessorTurmaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ProfessorTurmaNaoEncontradoException(Long professorTurmaId) {
		this(String.format("Não existe um cadastro de professorTurma com código %d", professorTurmaId));
	}

}
