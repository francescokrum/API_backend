package org.example.cati.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.cati.model.cliente.Cliente;
import org.example.cati.model.cliente.dto.ClienteDTO;
import org.example.cati.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("*")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping("/cadastrarCliente")
    @Transactional
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody @Valid Cliente cliente,  UriComponentsBuilder uriBuilder) throws Exception {
        this.service.cadastrarCliente(cliente);
        URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @GetMapping("{id}")
    @Transactional
    public ClienteDTO buscarCliente(@PathVariable Long id) {
        return this.service.buscarClientePorId(id);
    }

    @GetMapping
    @Transactional
    public List<ClienteDTO> listarClientes() {
        return this.service.buscarClientes();
    }

    @PutMapping("/editarCliente")
    @Transactional
    public ResponseEntity editarCliente(@RequestBody Cliente cliente) {

        this.service.editarCliente(cliente);
        return ResponseEntity.ok().body(cliente);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity removerCliente(@PathVariable Long id) {
        this.service.removerCliente(id);
        return ResponseEntity.noContent().build();
    }
}
