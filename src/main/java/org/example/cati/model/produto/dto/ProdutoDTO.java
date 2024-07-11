package org.example.cati.model.produto.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.example.cati.model.unidade.UnidadeDeNegocio;

public interface ProdutoDTO {

    Long getId();
    String getNome();
    UnidadeDeNegocio getUnidadeDeNegocio();

}
