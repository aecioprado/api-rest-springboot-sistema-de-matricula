package com.users.domain.exception;

import org.springframework.http.HttpStatus;

// Cria um OBJETO DE EXCESSÃO


public class MateriaException extends RuntimeException{

	private static final long serialVersionUID = 6480589533072733255L;
	
	// constante http
	private final HttpStatus httpStatus;
	
	// construtor
	public MateriaException(final String mensagem, final HttpStatus httpStatus) {
		super(mensagem); // herança de RuntimeException
		this.httpStatus = httpStatus;
	}
	
	
	

}
 