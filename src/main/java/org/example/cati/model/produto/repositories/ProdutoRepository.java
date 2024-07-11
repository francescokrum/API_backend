package org.example.cati.model.produto.repositories;

import org.example.cati.model.produto.Produto;
import org.example.cati.model.produto.dto.ProdutoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Produto getById(long id);
    Optional<Produto> findById(Long id);
    List<ProdutoDTO> findAllBy();

    boolean existsByNome(String nome);
}
