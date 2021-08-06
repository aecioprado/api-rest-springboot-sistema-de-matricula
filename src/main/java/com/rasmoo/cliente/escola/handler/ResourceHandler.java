package com.rasmoo.cliente.escola.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rasmoo.cliente.escola.exception.MateriaException;
import com.rasmoo.cliente.escola.model.ErrorResponse;
import com.rasmoo.cliente.escola.model.ErrorResponse.ErrorResponseBuilder;

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
