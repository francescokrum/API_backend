package org.example.cati.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.cati.model.chamado.Chamado;
import org.example.cati.model.chamado.dto.ChamadoDTO;
import org.example.cati.service.ChamadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/chamado")
@CrossOrigin("*")
public class ChamadoController {

    private final ChamadoService service;

    public ChamadoController(ChamadoService service) {
        this.service = service;
    }

    @PostMapping("/cadastrarChamado")
    @Transactional
    public ResponseEntity cadastrarChamado(@RequestBody @Valid Chamado chamado, UriComponentsBuilder uriBuilder) {
        this.service.cadastrarChamado(chamado);
        URI uri = uriBuilder.path("/chamados/{id}").buildAndExpand(chamado.getId()).toUri();
        return ResponseEntity.created(uri).body(chamado);
    }

    @GetMapping
    @Transactional
    public List<ChamadoDTO> buscaChamados() {
        return this.service.buscarChamados();
    }

    @GetMapping("{id}")
    @Transactional
    public ChamadoDTO buscaChamado(@PathVariable Long id) {
        return this.service.buscarChamadoPorId(id);
    }

    @PutMapping("/editarChamado")
    @Transactional
    public ResponseEntity editarChamado(@RequestBody Chamado chamado) {
        this.service.editarChamado(chamado);
        return ResponseEntity.ok().body(chamado);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity deletarChamado(@PathVariable Long id) {
        this.service.removerChamado(id);
        return ResponseEntity.noContent().build();
    }
}
