package gestao.matriculas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestao.matriculas.domain.Materia;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long>{

}
