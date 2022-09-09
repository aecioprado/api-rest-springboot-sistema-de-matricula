package com.users.domain.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getters e setters
@NoArgsConstructor // construtor vazio
public class MateriaDto {

	
	private Long id;
	
	@NotBlank(message = "Informe o nome da matéria.")
	private String nome;

	@Min(value = 34, message = "Permitido o mínimo de 34 horas por matérias.")
	@Max(value = 34, message = "Permitido o máximo de 120 horas por matérias.")
	private int horas;

	@NotBlank(message = "Informe o código da matéria.")
	private String codigo;
	
	@Min(value = 1, message = "Permitido no mínimo 1 vez ao ano")
	@Max(value = 34, message = "Permitidono máximo 2 vezes ao ano.")
	private int frequencia;

}
