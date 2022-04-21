package gestao.matriculas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import gestao.matriculas.constants.enums.SexoEnum;
import gestao.matriculas.domain.Perfil;
import gestao.matriculas.domain.Usuario;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Data
public class UsuarioDto {

    private Integer id;
    private String nome;
    private SexoEnum sexo;
    private String cpf;
    private List<Perfil> perfis;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;
    private String email;
    private String login;
    private boolean ativo;
    private String senha;


    public static UsuarioDto semSenha(Usuario usuario){
        UsuarioDto usuarioDto = new UsuarioDto();

        Optional.ofNullable(usuario.getId()).ifPresent(usuarioDto::setId);
        Optional.ofNullable(usuario.getNome()).ifPresent(usuarioDto::setNome);
        Optional.ofNullable(usuario.getSexo()).ifPresent(usuarioDto::setSexo);
        Optional.ofNullable(usuario.getCpf()).ifPresent(usuarioDto::setCpf);
        Optional.ofNullable(usuario.getPerfis()).ifPresent(usuarioDto::setPerfis);
        Optional.ofNullable(usuario.getDataNascimento()).ifPresent(usuarioDto::setDataNascimento);
        Optional.ofNullable(usuario.getDataCadastro()).ifPresent(usuarioDto::setDataCadastro);
        Optional.ofNullable(usuario.getEmail()).ifPresent(usuarioDto::setEmail);
        Optional.ofNullable(usuario.getLogin()).ifPresent(usuarioDto::setLogin);
        Optional.ofNullable(usuario.isAtivo()).ifPresent(usuarioDto::setAtivo);

        return usuarioDto;
    }
}
