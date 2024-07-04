package org.example.cati.infra.security.auth;

import org.example.cati.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByLogin(String login);
}
