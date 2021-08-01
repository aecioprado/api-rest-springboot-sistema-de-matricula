package com.rasmoo.cliente.escola.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

// separar todas as exceções pelas devidas entidades

@Getter // gera apenas os métodos getter
public class MateriaException extends RuntimeException{

	private static final long serialVersionUID = 6480589533072733255L;
	
	// constante http
	private final HttpStatus httpStatus;
	
	// construtor
	public MateriaException(final String mensagem, final HttpStatus httpStatus) {
		super(mensagem); // herança ? ou polimorfimo ? estudar o super
		this.httpStatus = httpStatus;
	}
	
	
	

}
 