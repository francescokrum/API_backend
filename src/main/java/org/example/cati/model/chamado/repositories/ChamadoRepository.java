package org.example.cati.model.chamado.repositories;

import org.example.cati.model.chamado.Chamado;
import org.example.cati.model.chamado.dto.ChamadoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

    Chamado getById(Long id);
    Optional<Chamado> findById(Long id);
    List<ChamadoDTO> findAllBy();
}
