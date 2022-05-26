package gestao.matriculas.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String username;
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_roles",
            uniqueConstraints=@UniqueConstraint(columnNames={"usuario_id","role_id"}),
            joinColumns = @JoinColumn(name ="usuario_id", referencedColumnName = "id", table = "usuario", unique = false),
            inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id", table ="role", unique = false)
    )
    private List<Role> roles;

    public void addRole(Role role){
        this.roles.add(role);
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Usuario usuario = (Usuario) o;
        return id != null && Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
