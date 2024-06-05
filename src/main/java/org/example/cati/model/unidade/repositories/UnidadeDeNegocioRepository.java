package org.example.cati.model.unidade.repositories;

import org.example.cati.model.unidade.UnidadeDeNegocio;
import org.example.cati.model.unidade.dto.UnidadeDeNegocioDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.LinkedList;
import java.util.Optional;

public interface UnidadeDeNegocioRepository extends JpaRepository<UnidadeDeNegocio, Long> {

    UnidadeDeNegocio getById(Long id);
    Optional<UnidadeDeNegocio> findById(Long id);
    LinkedList<UnidadeDeNegocioDTO> findAllBy();

}
