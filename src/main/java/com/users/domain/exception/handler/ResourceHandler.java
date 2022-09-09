package com.users.domain.exception.handler;

import com.users.domain.exception.MateriaException;
import com.users.domain.exception.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.users.domain.exception.model.ErrorResponse.ErrorResponseBuilder;

@ControllerAdvice
public class ResourceHandler { // tratamento de uma MateriaException

		// Manipulador as excessões com objetos tipo ErrorResponse com metodos que recebem como parametro um objeto 
		// materiaException
	@ExceptionHandler(MateriaException.class)
	public ResponseEntity<ErrorResponse> handlerMateriaException(MateriaException m) {
		ErrorResponseBuilder erro = ErrorResponse.builder();
		erro.httpStatus(m.getHttpStatus().value());
		erro.mensagem(m.getMessage());
		erro.timeStamp(System.currentTimeMillis());
		return ResponseEntity.status(m.getHttpStatus()).body(erro.build());

	}

}
