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
    private Long id;
    private String nome;
    private String sobrenome;
    private LocalDate dataAniversario;
    private String login;
    private String email;
    private String senha;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_permissoes",
            uniqueConstraints=@UniqueConstraint(columnNames={"usuario_id","permissao_id"}),
            joinColumns = @JoinColumn(name ="usuario_id", referencedColumnName = "id", table = "usuario", unique = false),
            inverseJoinColumns = @JoinColumn(name="permissao_id", referencedColumnName = "id", table ="permissao", unique = false)
    )
    private List<Permissao> permissoes = new ArrayList<Permissao>();

    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    public Usuario(){};

    public Usuario(Long id, String nome, String sobrenome, LocalDate dataAniversario, String login, String email,
                   String senha, LocalDateTime dataCriacao, LocalDateTime dataModificacao) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataAniversario = dataAniversario;
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;

    }

    public Long getUsuarioId() {
        return id;
    }

    public void setUsuarioId(Long usuarioId) {
        this.id = usuarioId;
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


    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
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
