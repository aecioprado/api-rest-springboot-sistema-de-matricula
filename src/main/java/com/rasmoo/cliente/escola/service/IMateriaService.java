package com.rasmoo.cliente.escola.service;

import java.util.List;

import com.rasmoo.cliente.escola.entity.MateriaEntity;

// A interface IMateriaService define o contrato com a classe MateriaService de quais métodos devem ser implementados por ela.

public interface IMateriaService {
	
	public Boolean salvarMateria(final MateriaEntity materia);
	
	public MateriaEntity consultarPorId(final Long id); //ATENCAO A ASSINATURA DO METODO
	
	public List<MateriaEntity> listarMaterias();
	
	public Boolean atualizar(final MateriaEntity materia);
	
	public Boolean excluirMateria(final Long id);
	

}
