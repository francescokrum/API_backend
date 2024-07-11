package org.example.cati.model.tarefa.dto;

import org.example.cati.enums.status.StatusTarefa;

public interface TarefaDTO {

    Long getId();
    String getTitulo();
    String getDescricao();
    StatusTarefa getStatusTarefa();

}
