package gestao.matriculas.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String sexo;
    private String cpf;

    @OneToMany(fetch = FetchType.EAGER)
    // cria uma tabela auxiliar
    @JoinTable(name = "usuarios_perfis",
            uniqueConstraints=@UniqueConstraint(columnNames={"usuario_id","perfil_id"}),
            joinColumns = @JoinColumn(name ="usuario_id", referencedColumnName = "id", table = "usuario"),
            inverseJoinColumns = @JoinColumn(name="perfil_id", referencedColumnName = "id", table ="perfil")
    )
    private List<Perfil> perfis; // um usuário tem 1 ou mais perfis de acesso
    private String tipoUsuario;
    private LocalDate dataNascimento;
    private String idadeAtual;
    private LocalDate dataCadastro;
    private LocalDate ultimaModificacao;
    private LocalDate ultimoAcesso;
    private LocalDate criadoEm;
    private String email;
    private String login;
    private String senha;
    private boolean ativo;

}
