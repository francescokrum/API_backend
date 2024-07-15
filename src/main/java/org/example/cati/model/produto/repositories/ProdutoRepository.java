package org.example.cati.model.produto.repositories;

import org.example.cati.model.cliente.Cliente;
import org.example.cati.model.produto.Produto;
import org.example.cati.model.produto.dto.ProdutoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Produto getById(long id);
    Optional<Produto> findById(Long id);
    List<ProdutoDTO> findAllBy();

    @Query(value = "SELECT p.id_produto AS id_produto, p.nome_produto AS nome_produto, u.id_unidade AS id_unidade " +
            "FROM produto p " +
            "JOIN unidade_negocio u ON p.id_unidade = u.id_unidade " +
            "JOIN cliente c ON u.id_unidade = c.id_unidade " +
            "JOIN usuario us ON c.id_usuario = us.id_usuario " +
            "WHERE us.login = :login", nativeQuery = true)
    List<Produto> findProdutoByCliente( String login);



    boolean existsByNome(String nome);
}
