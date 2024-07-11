package org.example.cati.service;

import org.example.cati.model.tarefa.Tarefa;
import org.example.cati.model.tarefa.repositories.TarefaRepository;
import org.example.cati.model.desenvolvedor.Desenvolvedor;
import org.example.cati.model.desenvolvedor.repositories.DesenvolvedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final DesenvolvedorRepository desenvolvedorRepository;

    public TarefaService(TarefaRepository tarefaRepository, DesenvolvedorRepository desenvolvedorRepository) {
        this.tarefaRepository = tarefaRepository;
        this.desenvolvedorRepository = desenvolvedorRepository;
    }

    public void cadastrarTarefa(Tarefa tarefa) {
        Long desenvolvedor = desenvolvedorRepository.getById(tarefa.getDesenvolvedor().getId()).getId();
        if (desenvolvedor == null) {
            throw new RuntimeException("Desenvolvedor não encontrado");
        }

        this.tarefaRepository.save(tarefa);
    }

    public List<Tarefa> buscarTarefas() {
        return this.tarefaRepository.findAll();
    }

    public Tarefa buscarTarefaPorId(Long id) {
        return this.tarefaRepository.findById(id).get();
    }

    public void removerTarefa(Long id) {
        this.tarefaRepository.deleteById(id);
    }

    public void editarTarefa(Tarefa tarefa) {
        Tarefa tarefaExistente = this.tarefaRepository.getReferenceById(tarefa.getId());

        tarefaExistente.setTitulo(tarefa.getTitulo());
        tarefaExistente.setDescricao(tarefa.getDescricao());
        tarefaExistente.setStatus(tarefa.getStatus());

        Optional<Desenvolvedor> desenvolvedor = desenvolvedorRepository.findById(tarefa.getDesenvolvedor().getId());
        if (!desenvolvedor.isPresent()) {
            throw new RuntimeException("Desenvolvedor não encontrado");
        }
        tarefaExistente.setDesenvolvedor(desenvolvedor.get());

        this.tarefaRepository.save(tarefaExistente);
    }
}
