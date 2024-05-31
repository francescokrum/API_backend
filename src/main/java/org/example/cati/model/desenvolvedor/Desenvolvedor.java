package org.example.cati.model.desenvolvedor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.cati.model.usuario.Usuario;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "desenvolvedor")
@Table(name = "desenvolvedor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Desenvolvedor extends Usuario {

    private Date dataNasc;
    private BigDecimal salario;
    @NotBlank
    private String cargo;

}
