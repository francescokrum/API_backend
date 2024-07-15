package org.example.cati.model.chamado.dto;

import org.example.cati.enums.gravidade.Gravidade;
import org.example.cati.enums.status.StatusChamado;

public record ChamadoDTO(Long id, String titulo, String descricao, StatusChamado status, Gravidade gravidade) {

}
