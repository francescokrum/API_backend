package org.example.cati.model.tarefa.dto;

import org.example.cati.enums.status.StatusTarefa;

public record TarefaDTO(Long id, String titulo, String descricao, StatusTarefa status) {
}
