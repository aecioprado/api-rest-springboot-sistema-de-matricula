package com.users.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;
    //TODO
    //private UUID uuid;
    private String nome;
    private String sobrenome;
    private LocalDate dataAniversario;
    private String login;
    private String email;
    private String senha;
    private Categoria categoria;
    private Endereco endereco;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_permissoes",
            uniqueConstraints=@UniqueConstraint(columnNames={"usuarioId","permissaoId"}),
            joinColumns = @JoinColumn(name ="usuario_id", referencedColumnName = "id", table = "usuario", unique = false),
            inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id", table ="role", unique = false)
    )
    private List<Permissao> permissaos = new ArrayList<>();

    // Campos de auditoria
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    public Usuario(){};

    public Usuario(Long usuarioId, String nome, String sobrenome, LocalDate dataAniversario, String login, String email,
                   String senha, LocalDateTime dataCriacao, LocalDateTime dataModificacao, Categoria categoria, Endereco endereco) {
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataAniversario = dataAniversario;
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
        this.categoria = categoria;
        this.endereco = endereco;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public LocalDate getDataAniversario() {
        return dataAniversario;
    }

    public void setDataAniversario(LocalDate dataAniversario) {
        this.dataAniversario = dataAniversario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Permissao> getPermissaos() {
        return permissaos;
    }

    public void setPermissaos(List<Permissao> permissaos) {
        this.permissaos = permissaos;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
}
