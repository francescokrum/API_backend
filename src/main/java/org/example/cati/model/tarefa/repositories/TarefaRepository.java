package org.example.cati.model.tarefa.repositories;

import org.example.cati.model.tarefa.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    Tarefa getById(Long id);
    Optional<Tarefa> findById(Long id);
    List<Tarefa> findAll();

}
