package com.users.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;



@Entity
@Table(name = "tb_materia")

public class Materia implements Serializable {
	
	@Id // chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonInclude(Include.NON_NULL) // define que o campo nao pode ser nulo
	@Column(name ="id") // define o nome da coluna
	private Long id; // pq long ?
	
	@JsonInclude(Include.NON_EMPTY) // campo nao pode ser vazio
	@Column(name ="nome")
	private String nome;
	
	@Column(name ="hrs")
	private int horas; // pq int em vez de Interger ? "boxing/unboxing" ?
	
	@JsonInclude(Include.NON_EMPTY) // campo nao pode ser vazio
	@Column(name ="cod")
	private String codigo;
	
	@Column(name = "freq")
	private int frequencia;
	
	
	

}
