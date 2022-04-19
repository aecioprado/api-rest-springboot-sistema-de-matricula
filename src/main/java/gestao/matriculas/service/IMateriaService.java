package gestao.matriculas.service;

import java.util.List;

import gestao.matriculas.domain.Materia;

public interface IMateriaService {
	
	public Boolean salvarMateria(final Materia materia);
	
	public Materia consultarPorId(final Long id); //ATENCAO A ASSINATURA DO METODO
	
	public List<Materia> listarMaterias();
	
	public Boolean atualizar(final Materia materia);
	
	public Boolean excluirMateria(final Long id);
	

}
