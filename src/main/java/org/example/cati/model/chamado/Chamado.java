package org.example.cati.model.chamado;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.cati.enums.gravidade.Gravidade;
import org.example.cati.enums.status.StatusChamado;
import org.example.cati.model.produto.Produto;
import org.example.cati.model.cliente.Cliente;

@Entity(name = "chamado")
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chamado{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chamado")
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @Enumerated(EnumType.STRING)
    private StatusChamado status;
    @Enumerated(EnumType.STRING)
    private Gravidade gravidade;
    private byte[] recurso;

    public Chamado(StatusChamado status) {
        this.status = StatusChamado.SOLICITADO;
    }

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;
}
