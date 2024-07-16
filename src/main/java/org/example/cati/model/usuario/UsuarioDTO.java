package org.example.cati.model.usuario;

import org.example.cati.enums.permissao.Permissao;

public record UsuarioDTO(Long id, String nome, String cpf, String email, Permissao permissao) {
}
