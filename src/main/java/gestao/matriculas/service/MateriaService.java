package gestao.matriculas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import gestao.matriculas.exception.MateriaException;
import gestao.matriculas.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import gestao.matriculas.domain.Materia;

@Service // Bean
public class MateriaService implements IMateriaService {

	@Autowired
	private MateriaRepository materiaRepository;

	@Override
	public Boolean atualizar(Materia materia) {

		// instancia um novo objeto entity que recebe como atribuição o id do objeto
		// passado no método

		try {

			Optional<Materia> materiaOptional = this.materiaRepository.findById(materia.getId());

			if (materiaOptional.isPresent()) {
				Materia materiaEntityAtualizada = materiaOptional.get();

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
			this.consultarPorId(id);
			this.materiaRepository.deleteById(id);
			return true;
		} catch (MateriaException m) {
			throw m;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Boolean salvarMateria(Materia materia) {
		try {
			this.materiaRepository.save(materia);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Materia consultarPorId(Long id) {
		try {
			Optional<Materia> materialOptional = this.materiaRepository.findById(id); // Atenção ao optional

			// validar
			if (materialOptional.isPresent()) {
				return materialOptional.get(); // retorna o objeto se ele existir
			}
			throw new MateriaException("Materia não encontrada", HttpStatus.NOT_FOUND);

		} catch (MateriaException m) {
			throw m;
		} catch (Exception e) {
			throw new MateriaException("Erro interno identificado. Contate o suporte",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<Materia> listarMaterias() {

		try {
			return this.materiaRepository.findAll();
		} catch (Exception e) {
			return new ArrayList<>();
		}

	}

}
