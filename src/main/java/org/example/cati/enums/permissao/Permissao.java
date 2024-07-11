package org.example.cati.enums.permissao;

public enum Permissao {
    ADMIN("ROLE_ADMIN"),
    DEV("ROLE_DEV"),
    CLIENTE("ROLE_CLIENTE");

    private String permissao;

    Permissao(String permissao) {
        this.permissao = permissao;
    }

    public String getPermissao() {
        return permissao;
    }
}
