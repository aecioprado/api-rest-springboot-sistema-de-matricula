package gestao.matriculas.dto;

import gestao.matriculas.domain.Role;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class UsuarioDto {

    private Integer id;
    private String nome;
    private String cpf;
    private List<Role> perfis;
    private LocalDate dataNascimento;
    private LocalDate dataCadastro;
    private String email;
    private String login;
    private boolean ativo;
    private String senha;

}
