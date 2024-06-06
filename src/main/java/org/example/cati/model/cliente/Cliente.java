package org.example.cati.model.cliente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.cati.model.cliente.dto.ClienteDTO;
import org.example.cati.model.unidade.UnidadeDeNegocio;
import org.example.cati.model.usuario.Usuario;

@Entity(name = "cliente")
@Table(name = "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Usuario implements ClienteDTO {

    @NotBlank
    private String cnpj_unidade;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_unidade")
    private UnidadeDeNegocio unidadeDeNegocio;
}
