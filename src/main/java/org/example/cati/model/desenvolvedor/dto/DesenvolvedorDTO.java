package org.example.cati.model.desenvolvedor.dto;

import java.math.BigDecimal;
import java.util.Date;

public interface DesenvolvedorDTO {

    String getNome();
    String getCpf();
    String getEmail();
    String getPermissao();
    String getLogin();
    String getSenha();
    Date getDataNasc();
    BigDecimal getSalario();
    String getCargo();
}
