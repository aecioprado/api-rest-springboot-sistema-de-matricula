package gestao.matriculas.repository;

import gestao.matriculas.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findDistinctByEmail(String email);

    Optional<List<Usuario>> findByDataCadastroBetween(LocalDate dataInicial, LocalDate dataFinal);
}
