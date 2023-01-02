package com.borges.diario_eletronico.domain.exception;

public class SerieNivelSubnivelNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	
	public SerieNivelSubnivelNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public SerieNivelSubnivelNaoEncontradoException(Long serieId) {
		this(String.format("Não existe um cadastro de serie com código %d", serieId));
	}

}
