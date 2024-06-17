package org.example.cati.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.cati.model.desenvolvedor.Desenvolvedor;
import org.example.cati.model.desenvolvedor.dto.DesenvolvedorDTO;
import org.example.cati.service.DesenvolvedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/desenvolvedor")
@CrossOrigin("*")
public class DesenvolvedorController {

    private final DesenvolvedorService service;

    public DesenvolvedorController(DesenvolvedorService service) {
        this.service = service;
    }

    @PostMapping("/cadastrarDesenvolvedor")
    @Transactional
    public ResponseEntity cadastrarDesenvolvedor(@RequestBody @Valid Desenvolvedor desenvolvedor, UriComponentsBuilder uriBuilder) {
        this.service.cadastrarDev(desenvolvedor);
        URI uri = uriBuilder.path("/desenvolvedor/{id}").buildAndExpand(desenvolvedor.getId()).toUri();
        return ResponseEntity.created(uri).body(desenvolvedor);
    }

    @GetMapping
    @Transactional
    public List<DesenvolvedorDTO> buscaDesenvolvedores() {
        return this.service.buscarDevs();
    }

    @GetMapping("{id}")
    @Transactional
    public Desenvolvedor buscaDesenvolvedor(@PathVariable Long id) {
        return this.service.buscarDevPorId(id);
    }

    @PutMapping("/editarDesenvolvedor")
    @Transactional
    public ResponseEntity editarDesenvolvedor(@RequestBody Desenvolvedor desenvolvedor){
        this.service.editarDev(desenvolvedor);
        return ResponseEntity.ok().body(desenvolvedor);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity deletarDesenvolvedor(@PathVariable Long id) {
        this.service.removerDev(id);
        return ResponseEntity.noContent().build();
    }

}

