package com.rasmoo.cliente.escola.service;

import java.util.List;

import com.rasmoo.cliente.escola.entity.MateriaEntity;

public interface IMateriaService {
	
	public Boolean cadastar(final MateriaEntity materia);
	
	public MateriaEntity consultaPorId(final Long id); //ATENCAO A ASSINATURA DO METODO
	
	public List<MateriaEntity> listarMaterias();
	
	public Boolean atualizar(final MateriaEntity materia);
	
	public Boolean excluir(final Long id);
	

}
