package com.borges.diario_eletronico.domain.exception;

public class ProfessorTurmaDisciplinaNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public ProfessorTurmaDisciplinaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ProfessorTurmaDisciplinaNaoEncontradoException(Long professorTurmaDisciplinaId) {
		this(String.format("Não existe um cadastro de professorTurmaDisciplina com código %d", professorTurmaDisciplinaId));
	}

}
