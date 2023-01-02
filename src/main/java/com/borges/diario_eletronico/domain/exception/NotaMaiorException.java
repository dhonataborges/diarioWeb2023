package com.borges.diario_eletronico.domain.exception;

public class NotaMaiorException extends NegocioException{

	private static final long serialVersionUID = 1L;
	
	public NotaMaiorException(String mensagem) {
		super(mensagem);
	}
	
	public NotaMaiorException(Double notaMaior) {
		this(String.format("Nota é maior que a nota maxíma! ", notaMaior));
	}

}
