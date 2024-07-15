package org.example.cati.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.example.cati.model.tarefa.Tarefa;
import org.example.cati.model.tarefa.dto.TarefaDTO;
import org.example.cati.service.TarefaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tarefa")
@CrossOrigin("*")
public class TarefaController {

    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @PostMapping("/cadastrarTarefa")
    @Transactional
    public ResponseEntity cadastrarTarefa(@RequestBody @Valid Tarefa tarefa, UriComponentsBuilder uriBuilder) {
        this.service.cadastrarTarefa(tarefa);
        URI uri = uriBuilder.path("/tarefas/{id}").buildAndExpand(tarefa.getId()).toUri();
        return ResponseEntity.created(uri).body(tarefa);
    }

    @GetMapping
    @Transactional
    public List<Tarefa> buscaTarefas() {
        return this.service.buscarTarefas();
    }

    @GetMapping("/buscarTarefaPorDev")
    @Transactional
    public List<TarefaDTO> buscarTarefaPorDev(HttpServletRequest request){
        return this.service.buscarTarefaPorDev(request);
    }

    @GetMapping("{id}")
    @Transactional
    public Tarefa buscaTarefa(@PathVariable Long id) {
        return this.service.buscarTarefaPorId(id);
    }

    @PutMapping("/editarTarefa")
    @Transactional
    public ResponseEntity editarTarefa(@RequestBody Tarefa tarefa) {
        this.service.editarTarefa(tarefa);
        return ResponseEntity.ok().body(tarefa);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity deletarTarefa(@PathVariable Long id) {
        this.service.removerTarefa(id);
        return ResponseEntity.noContent().build();
    }
}
