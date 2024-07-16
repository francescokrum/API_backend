package org.example.cati.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.cati.model.cliente.Cliente;
import org.example.cati.model.cliente.dto.ClienteDTO;
import org.example.cati.model.usuario.UsuarioDTO;
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

    @GetMapping("/buscarTodosUsuarios")
    @Transactional
    public List<UsuarioDTO> buscarTodosUsuarios(){
        return this.service.listarUsuarios();
    }

    @GetMapping
    @Transactional
    public ClienteDTO buscarCliente(HttpServletRequest request) {
        return this.service.buscarClientePorId(request);
    }

    @GetMapping("/clientes")
    @Transactional
    public List<Cliente> listarClientes() {
        return this.service.buscarClientes();
    }

    @PutMapping("/editarCliente")
    @Transactional
    public ResponseEntity editarCliente(@RequestBody ClienteDTO cliente, HttpServletRequest request) {

        this.service.editarCliente(cliente, request);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity removerCliente(@PathVariable Long id) {
        this.service.removerCliente(id);
        return ResponseEntity.noContent().build();
    }
}
