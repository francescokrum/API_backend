package org.example.cati.model.desenvolvedor.repositories;

import org.example.cati.model.desenvolvedor.Desenvolvedor;
import org.example.cati.model.desenvolvedor.dto.DesenvolvedorDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.Optional;

@Repository
public interface DesenvolvedorRepository extends JpaRepository<Desenvolvedor, Long> {

    Desenvolvedor getById(Long id_usuario);
    Optional<Desenvolvedor> findById(Long id_usuario);
    LinkedList<DesenvolvedorDTO> findAllBy();
    Desenvolvedor findByLogin(String login);

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);
}
