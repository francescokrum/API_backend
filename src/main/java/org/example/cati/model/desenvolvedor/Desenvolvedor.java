package org.example.cati.model.desenvolvedor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.cati.model.chamado.Chamado;
import org.example.cati.model.desenvolvedor.dto.DesenvolvedorDTO;
import org.example.cati.enums.permissao.Permissao;
import org.example.cati.model.tarefa.Tarefa;
import org.example.cati.model.usuario.Usuario;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    public Desenvolvedor(Long id, @NotBlank String nome, @NotBlank String cpf,
                         @NotBlank String email, Permissao permissao, @NotBlank String login,
                         @NotBlank String senha, Date dataNasc, BigDecimal salario, String cargo) {

        super(id, nome, cpf, email, permissao.DEV, login, senha);
        this.dataNasc = dataNasc;
        this.salario = salario;
        this.cargo = cargo;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "desenvolvedor")
    private List<Tarefa> tarefas;
}
