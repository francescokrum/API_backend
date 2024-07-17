package org.example.cati.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.cati.model.unidade.UnidadeDeNegocio;
import org.example.cati.model.unidade.dto.UnidadeDTO;
import org.example.cati.service.UnidadeDeNegocioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/unidadeDeNegocio")
@CrossOrigin("*")
public class UnidadeDeNegocioController {

    private final UnidadeDeNegocioService service;

    public UnidadeDeNegocioController(UnidadeDeNegocioService service) {
        this.service = service;
    }

    @PostMapping("/cadastrarUnidadeDeNegocio")
    @Transactional
    public ResponseEntity cadastrarUnidadeDeNegocio(@RequestBody @Valid UnidadeDeNegocio unidadeDeNegocio, UriComponentsBuilder uriBuilder) {

        this.service.cadastrarUnidadeDeNegocio(unidadeDeNegocio);
        URI uri = uriBuilder.path("/unidadeDeNegocio/{id}").buildAndExpand(unidadeDeNegocio.getId()).toUri();
        return ResponseEntity.created(uri).body(unidadeDeNegocio);
    }

    @GetMapping
    @Transactional
    public List<UnidadeDTO> buscaUnidadesDeNegocio() {
        return this.service.buscarUnidadeDeNegocio();
    }

    @GetMapping("{id}")
    @Transactional
    public Optional<UnidadeDeNegocio> buscaUnidadeDeNegocio(@PathVariable Long id) {
            return this.service.buscarUnidadeDeNegocioPorId(id);
    }

    @PutMapping("/editarUnidadeDeNegocio")
    @Transactional
    public ResponseEntity editarUnidadeDeNegocio(@RequestBody UnidadeDeNegocio unidadeDeNegocio) {
        this.service.editarUnidadeDeNegocio(unidadeDeNegocio);
        return ResponseEntity.ok().body(unidadeDeNegocio);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity deletarUnidadeDeNegocio(@PathVariable Long id) {
        this.service.removerUnidadeDeNegocio(id);
        return ResponseEntity.noContent().build();
    }
}
