package com.users.domain.model;

import org.apache.logging.log4j.message.StringFormattedMessage;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Permissao implements Serializable{
    @Id
    private Long permissaoId;
    private String descricao;

    public Permissao(){};

    public Permissao(long permissaoId, String descricao){
        this.permissaoId = permissaoId;
        this.descricao = descricao;
    }

    public Long getPermissaoId() {
        return permissaoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setPermissaoId(Long permissaoId) {
        this.permissaoId = permissaoId;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permissao permissao = (Permissao) o;
        return Objects.equals(permissaoId, permissao.permissaoId) && Objects.equals(descricao, permissao.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissaoId, descricao);
    }
}
