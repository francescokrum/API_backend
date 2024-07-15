package org.example.cati.model.tarefa.repositories;

import org.example.cati.model.tarefa.Tarefa;
import org.example.cati.model.tarefa.dto.TarefaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    Tarefa getById(Long id);
    Optional<Tarefa> findById(Long id);
    List<TarefaDTO> findAllBy();

    @Query(value = "SELECT t.* " +
            "FROM tarefa t " +
            "JOIN desenvolvedor d ON t.id_usuario = d.id_usuario " +
            "JOIN usuario u ON d.id_usuario = u.id_usuario " +
            "WHERE u.login = :login", nativeQuery = true)
    List<Tarefa> buscarTarefaPorDev(String login);

}
