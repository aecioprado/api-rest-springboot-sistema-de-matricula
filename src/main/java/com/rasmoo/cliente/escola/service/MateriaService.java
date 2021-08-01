package com.rasmoo.cliente.escola.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasmoo.cliente.escola.entity.MateriaEntity;
import com.rasmoo.cliente.escola.repository.IMateriaRepository;

@Service // Bean
public class MateriaService implements IMateriaService {

	@Autowired
	private IMateriaRepository materiaRepository;

	@Override
	public Boolean atualizar(MateriaEntity materia) {
		try {

			// instancia um novo objeto entity que recebe como atribuição o id do objeto
			// passado no método
			MateriaEntity materiaEntityAtualizada = this.materiaRepository.findById(materia.getId()).get();

			// atualizamos todos os valores
			materiaEntityAtualizada.setNome(materia.getNome());
			materiaEntityAtualizada.setCodigo(materia.getCodigo());
			materiaEntityAtualizada.setHoras(materia.getHoras());
			materiaEntityAtualizada.setNome(materia.getNome());
			materiaEntityAtualizada.setFrequencia(materia.getFrequencia());

			// salvamos as alteracoes
			this.materiaRepository.save(materiaEntityAtualizada);

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean excluir(Long id) {
		try {
			this.materiaRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean cadastar(MateriaEntity materia) {
		try {
			this.materiaRepository.save(materia);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public MateriaEntity consultaPorId(Long id) {
		try {
			Optional<MateriaEntity> materialOptional = this.materiaRepository.findById(id);

			// validar
			if (materialOptional.isPresent()) {
				return materialOptional.get(); // retorna o objeto se ele existir
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@Override
	public List<MateriaEntity> listarMaterias() {
		
		try {
			return this.materiaRepository.findAll();
		} catch(Exception e) {
			return new ArrayList<>();
		}
		
		
	}

}
