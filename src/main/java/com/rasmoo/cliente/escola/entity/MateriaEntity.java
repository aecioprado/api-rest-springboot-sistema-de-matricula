package com.rasmoo.cliente.escola.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // informa que a classe é uma entidade no banco de dados
@Table(name = "tb_materia") // opcional: defini o nome da tabela no banco de dados
@NoArgsConstructor // criar um construtor vazio
@Data // abstrai a criacao de getters and setters

public class MateriaEntity implements Serializable {
	// Obrigatorio serializar para garantir a integridade das informacoes
	// regristradas

	private static final long serialVersionUID = -3542212148069525221L;
	
	// atributos -> colunas da tabela
	
	@Id // chave primaria
	@GeneratedValue(generator = "increment") // define como regra que a criacao de Ids sera de 1 em 1
	@GenericGenerator(name = "increment", strategy = "increment")
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
