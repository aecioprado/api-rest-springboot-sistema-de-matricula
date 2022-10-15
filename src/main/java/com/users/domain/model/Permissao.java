package com.users.domain.model;

import org.apache.logging.log4j.message.StringFormattedMessage;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Permissao implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;

    public Permissao(){};

    public Permissao(long id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    public Long getPermissaoId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setPermissaoId(Long permissaoId) {
        this.id = permissaoId;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permissao permissao = (Permissao) o;
        return Objects.equals(id, permissao.id) && Objects.equals(descricao, permissao.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao);
    }
}
