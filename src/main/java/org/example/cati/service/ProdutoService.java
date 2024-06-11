package org.example.cati.service;

import org.example.cati.model.cliente.Cliente;
import org.example.cati.model.produto.Produto;
import org.example.cati.model.produto.dto.ProdutoDTO;
import org.example.cati.model.produto.repositories.ProdutoRepository;
import org.example.cati.model.unidade.UnidadeDeNegocio;
import org.example.cati.model.unidade.repositories.UnidadeDeNegocioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final UnidadeDeNegocioRepository unidadeDeNegocioRepository;

    public ProdutoService(ProdutoRepository repository, UnidadeDeNegocioRepository unidadeDeNegocioRepository) {
        this.repository = repository;
        this.unidadeDeNegocioRepository = unidadeDeNegocioRepository;
    }

    public void cadastrarProduto(Produto produto) {

        if (produto.getUnidadeDeNegocio() != null) {
            UnidadeDeNegocio unidadeDeNegocio = produto.getUnidadeDeNegocio();
            Optional<UnidadeDeNegocio> unidadeExistente = unidadeDeNegocioRepository.findById(unidadeDeNegocio.getId());
            if (unidadeExistente.isPresent()) {
                produto.setUnidadeDeNegocio(unidadeExistente.get());
            } else {
                throw new RuntimeException("Unidade de negócio não encontrada.");
            }
        } else {
            throw new RuntimeException("Unidade de negócio não pode ser nula.");
        }

        repository.save(produto);
    }

    public List<ProdutoDTO> buscarProdutos(){
        return repository.findAllBy();
    }

    public Produto buscarProdutoPorId(Long id) {
        return repository.findById(id).get();
    }

    public void removerProduto(Long id) {
        repository.deleteById(id);
    }


    public void editarProduto(Produto produto) {
        Produto prod = this.repository.getReferenceById(produto.getId());

        prod.setNome(produto.getNome());

        if(!unidadeDeNegocioRepository.existsById(produto.getUnidadeDeNegocio().getId())){

            prod.setUnidadeDeNegocio(produto.getUnidadeDeNegocio());
            Optional<UnidadeDeNegocio> novaUnidade = unidadeDeNegocioRepository.findById(produto.getUnidadeDeNegocio().getId());

            if(novaUnidade.isPresent()){
                prod.setUnidadeDeNegocio(novaUnidade.get());
            } else {
                throw new RuntimeException("Unidade de negócio fornecida não encontrada.");
            }
        }

        this.repository.save(prod);
    }
}
