package org.example.cati.model.desenvolvedor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.cati.model.desenvolvedor.dto.DesenvolvedorDTO;
import org.example.cati.model.usuario.Usuario;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "desenvolvedor")
@Table(name = "desenvolvedor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Desenvolvedor extends Usuario implements DesenvolvedorDTO {

    private Date dataNasc;
    @Column(columnDefinition = "MONEY")
    private BigDecimal salario;
    @NotBlank
    private String cargo;

}
