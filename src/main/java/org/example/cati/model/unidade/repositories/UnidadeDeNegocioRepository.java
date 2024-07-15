package org.example.cati.model.unidade.repositories;

import org.example.cati.model.unidade.UnidadeDeNegocio;
import org.example.cati.model.unidade.dto.UnidadeDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UnidadeDeNegocioRepository extends JpaRepository<UnidadeDeNegocio, Long> {

    Optional<UnidadeDeNegocio> findById(Long id);
    Optional<UnidadeDeNegocio> findByCnpj(String cnpj);
    List<UnidadeDTO> findAllBy();

    boolean existsById(Long id);

}
