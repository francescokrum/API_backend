package org.example.cati.enums.gravidade;

import lombok.Getter;

@Getter
public enum Gravidade {

    LEVE("LEVE"), MEDIA("MEDIA"), URGENTE("URGENTE");

    private String gravidade;

    Gravidade(String gravidade) {
        this.gravidade = gravidade;
    }
}
