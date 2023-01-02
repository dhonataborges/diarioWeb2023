package com.borges.diario_eletronico.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeDuplicadaException extends NegocioException {	
	private static final long serialVersionUID = 1L;
	
	public EntidadeDuplicadaException(String mensagem) {
		super(mensagem);
	}

}
