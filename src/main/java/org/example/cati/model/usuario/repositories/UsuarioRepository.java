package org.example.cati.model.usuario.repositories;

import org.example.cati.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCpf(String cpf);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByLogin(String login);
    Optional<Usuario> findBySenha(String senha);
}
