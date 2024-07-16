package org.example.cati.model.chamado.repositories;

import org.example.cati.model.chamado.Chamado;
import org.example.cati.model.chamado.dto.ChamadoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

    Chamado getById(Long id);
    Optional<Chamado> findById(Long id);
    List<Chamado> findAllBy();

    @Query(value = "SELECT c.id_chamado, c.titulo, c.descricao, c.status, c.gravidade, c.recurso, cli.id_usuario, p.id_produto " +
            "FROM chamado c " +
            "JOIN cliente cli ON c.id_usuario = cli.id_usuario " +
            "JOIN usuario u ON cli.id_usuario = u.id_usuario " +
            "JOIN produto p ON c.id_produto = p.id_produto " +
            "WHERE u.login = :login", nativeQuery = true)
    List<Chamado> findAllByCliente(String login);


}
