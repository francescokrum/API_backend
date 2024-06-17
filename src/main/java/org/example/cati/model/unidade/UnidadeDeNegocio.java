package org.example.cati.model.unidade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.cati.model.cliente.Cliente;
import org.example.cati.model.produto.Produto;
import org.example.cati.model.unidade.dto.UnidadeDeNegocioDTO;

import java.util.List;

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

    @OneToMany(mappedBy = "unidadeDeNegocio")
    private List<Cliente> clientes;

    @JsonIgnore
    @OneToMany(mappedBy = "unidadeDeNegocio", cascade = CascadeType.ALL)
    private List<Produto> produtos;
}
