package org.example.cati.model.cliente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.cati.model.chamado.Chamado;
import org.example.cati.model.cliente.dto.ClienteDTO;
import org.example.cati.enums.permissao.Permissao;
import org.example.cati.model.unidade.UnidadeDeNegocio;
import org.example.cati.model.usuario.Usuario;

import java.util.List;

@Entity(name = "cliente")
@Table(name = "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Usuario implements ClienteDTO {

    @NotBlank
    private String cnpj_unidade;

    public Cliente(Long id, @NotBlank String nome, @NotBlank String cpf, @NotBlank String email, Permissao permissao,
                   @NotBlank String login, @NotBlank String senha, String cnpj_unidade, UnidadeDeNegocio unidadeDeNegocio) {
        super(id, nome, cpf, email, permissao.CLIENTE, login, senha);
        this.cnpj_unidade = cnpj_unidade;
        this.unidadeDeNegocio = unidadeDeNegocio;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_unidade")
    private UnidadeDeNegocio unidadeDeNegocio;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados;
}
