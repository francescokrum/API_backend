package org.example.cati.model.desenvolvedor.dto;

import org.example.cati.enums.permissao.Permissao;

import java.math.BigDecimal;
import java.util.Date;

public record DesenvolvedorDTO(Long id, String nome, String cpf, String email, String login, String senha,
                               Date dataNasc, BigDecimal salario, String cargo) {

}
