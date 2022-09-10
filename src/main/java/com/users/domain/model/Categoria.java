package com.users.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
@Entity
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoriaId;

    private String descricao;

    public Categoria(){};

    public Categoria(long categoriaId, String descricao){
        this.categoriaId = categoriaId;
        this.descricao = descricao;
    }

    public long getCategoriaId() {
        return categoriaId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setCategoriaId(long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return categoriaId == categoria.categoriaId && Objects.equals(descricao, categoria.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoriaId, descricao);
    }
}
