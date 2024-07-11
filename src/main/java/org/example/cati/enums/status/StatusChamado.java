package org.example.cati.enums.status;

import lombok.Getter;

@Getter
public enum StatusChamado {

    SOLICITADO("SOLICITADO"), EM_ANALISE("EM ANALISE"), FINALIZADO("FINALIZADO");

    private String statusChamado;

    StatusChamado(String statusChamado) {
        this.statusChamado = statusChamado;
    }

}
