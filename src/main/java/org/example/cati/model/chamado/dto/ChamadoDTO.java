package org.example.cati.model.chamado.dto;

public interface ChamadoDTO {

    String getTitulo();
    String getDescricao();
    Enum getStatus();
    Enum getGravidade();
    byte[] getRecurso();
}
