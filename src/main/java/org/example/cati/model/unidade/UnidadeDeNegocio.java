package org.example.cati.model.unidade;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.cati.model.cliente.Cliente;
import org.example.cati.model.produto.Produto;

import java.util.List;

@Entity(name = "unidade_negocio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UnidadeDeNegocio{

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

    @OneToMany(mappedBy = "unidadeDeNegocio", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Produto> produtos;
}
