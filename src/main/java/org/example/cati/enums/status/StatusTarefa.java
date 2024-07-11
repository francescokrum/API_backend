package org.example.cati.enums.status;

import lombok.Getter;

@Getter
public enum StatusTarefa {

    ENVIADA("ENVIADA"), EM_ANALISE("EM DESEVOLVIMENTO"), FINALIZADO("FINALIZADO");

    private String statusTarefa;

    StatusTarefa(String statusTarefa) {
        this.statusTarefa = statusTarefa;
    }
}
