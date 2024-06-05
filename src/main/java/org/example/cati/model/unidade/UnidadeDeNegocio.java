package org.example.cati.model.unidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.cati.model.unidade.dto.UnidadeDeNegocioDTO;

@Entity(name = "unidade_negocio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UnidadeDeNegocio implements UnidadeDeNegocioDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unidade")
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String cnpj;
}
