package org.example.cati.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.cati.model.produto.Produto;
import org.example.cati.model.produto.dto.ProdutoDTO;
import org.example.cati.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produto")
@CrossOrigin("*")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping("/cadastrarProduto")
    @Transactional
    public ResponseEntity cadastrarProduto(@RequestBody @Valid Produto produto, UriComponentsBuilder uriBuilder) {
        this.service.cadastrarProduto(produto);
        URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @GetMapping
    @Transactional
    public List<ProdutoDTO> buscaProdutos() {
        return this.service.buscarProdutos();
    }

    @GetMapping("{id}")
    @Transactional
    public Produto buscaProduto(@PathVariable Long id) {
        return this.service.buscarProdutoPorId(id);
    }

    @GetMapping("buscarProdutosPorCliente")
    @Transactional
    public List<ProdutoDTO> buscarProdutosPorCliente(HttpServletRequest request) {
        return this.service.buscarProdutosPorCliente(request);
    }

    @PutMapping("/editarProduto")
    @Transactional
    public ResponseEntity editarProduto(@RequestBody Produto produto){
        this.service.editarProduto(produto);
        return ResponseEntity.ok().body(produto);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity deletarProduto(@PathVariable Long id) {
        this.service.removerProduto(id);
        return ResponseEntity.noContent().build();
    }
}
