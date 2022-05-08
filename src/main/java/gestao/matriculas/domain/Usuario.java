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
    private Long id;
    private String nome;
    @Column(nullable = false)
    private String email;
    @Column(nullable = true, length = 11)
    private String cpf;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_perfis",
            uniqueConstraints=@UniqueConstraint(columnNames={"usuario_id","perfil_id"}),
            joinColumns = @JoinColumn(name ="usuario_id", referencedColumnName = "id", table = "usuario"),
            inverseJoinColumns = @JoinColumn(name="perfil_id", referencedColumnName = "id", table ="perfil")
    )
    private List<Perfil> perfis;

    @Column(nullable = true)
    private LocalDate dataNascimento;

    @Column(updatable = false)
    private LocalDate dataCadastro;

    @Column(nullable = false)
    private String senha;


    @PrePersist
    public void prePersistDataCadastro(){
        setDataCadastro(LocalDate.now());
    }

}
