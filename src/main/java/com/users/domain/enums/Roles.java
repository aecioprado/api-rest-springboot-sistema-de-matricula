package com.users.domain.enums;

public enum Roles {
    SUPER(1, "Super Usuario"),
    ADMIN(2, "Administrador"),
    USER(3, "Usuario"),
    VISITANTE(4, "Visitante");

    private int codigo;
    private String descricao;

    Roles(int _codigo, String _descricao) {

        codigo = _codigo;
        descricao = _descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
