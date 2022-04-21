package gestao.matriculas.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import gestao.matriculas.constants.enums.SexoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private SexoEnum sexo;

    @Column(nullable = true, length = 11)
    @CPF
    private String cpf;

    @OneToMany(fetch = FetchType.EAGER)
    // cria uma tabela auxiliar
    @JoinTable(name = "usuarios_perfis",
            uniqueConstraints=@UniqueConstraint(columnNames={"usuario_id","perfil_id"}),
            joinColumns = @JoinColumn(name ="usuario_id", referencedColumnName = "id", table = "usuario"),
            inverseJoinColumns = @JoinColumn(name="perfil_id", referencedColumnName = "id", table ="perfil")
    )
    private List<Perfil> perfis;

    @Column(nullable = true)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Column(updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, updatable = false)
    private String login;

    @Column(nullable = false)
    private String senha;

    private boolean ativo = Boolean.TRUE;

    // configura a data de criacao no momento da persistencia no banco
    @PrePersist
    public void prePersistDataCadastro(){
        setDataCadastro(LocalDate.now());
    }

}
