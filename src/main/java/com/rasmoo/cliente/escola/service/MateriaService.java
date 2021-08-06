package com.rasmoo.cliente.escola.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rasmoo.cliente.escola.entity.MateriaEntity;
import com.rasmoo.cliente.escola.exception.MateriaException;
import com.rasmoo.cliente.escola.repository.IMateriaRepository;

@Service // Bean
public class MateriaService implements IMateriaService {

	@Autowired
	private IMateriaRepository materiaRepository;

	@Override
	public Boolean atualizar(MateriaEntity materia) {

		// instancia um novo objeto entity que recebe como atribuição o id do objeto
		// passado no método

		try {

			Optional<MateriaEntity> materiaOptional = this.materiaRepository.findById(materia.getId());

			if (materiaOptional.isPresent()) {
				MateriaEntity materiaEntityAtualizada = materiaOptional.get();

				// atualizamos todos os valores
				materiaEntityAtualizada.setNome(materia.getNome());
				materiaEntityAtualizada.setCodigo(materia.getCodigo());
				materiaEntityAtualizada.setHoras(materia.getHoras());
				materiaEntityAtualizada.setNome(materia.getNome());
				materiaEntityAtualizada.setFrequencia(materia.getFrequencia());

				// salvamos as alteracoes
				this.materiaRepository.save(materiaEntityAtualizada);

				return true;
			}
			return false;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean excluirMateria(Long id) {
		try {
			this.materiaRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean salvarMateria(MateriaEntity materia) {
		try {
			this.materiaRepository.save(materia);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public MateriaEntity consultarPorId(Long id) {
		try {
			Optional<MateriaEntity> materialOptional = this.materiaRepository.findById(id); // Atenção ao optional

			// validar
			if (materialOptional.isPresent()) {
				return materialOptional.get(); // retorna o objeto se ele existir
			}
			throw new MateriaException("Materia não encontrada", HttpStatus.NOT_FOUND);

		} catch (MateriaException m) {
			throw m;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<MateriaEntity> listarMaterias() {

		try {
			return this.materiaRepository.findAll();
		} catch (Exception e) {
			return new ArrayList<>();
		}

	}

}
