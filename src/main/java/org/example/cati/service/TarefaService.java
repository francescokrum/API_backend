package org.example.cati.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.cati.infra.security.config.JwtServiceGenerator;
import org.example.cati.model.tarefa.Tarefa;
import org.example.cati.model.tarefa.dto.TarefaDTO;
import org.example.cati.model.tarefa.repositories.TarefaRepository;
import org.example.cati.model.desenvolvedor.Desenvolvedor;
import org.example.cati.model.desenvolvedor.repositories.DesenvolvedorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final DesenvolvedorRepository desenvolvedorRepository;
    private final JwtServiceGenerator jwt;

    public TarefaService(TarefaRepository tarefaRepository, DesenvolvedorRepository desenvolvedorRepository, JwtServiceGenerator jwt) {
        this.tarefaRepository = tarefaRepository;
        this.desenvolvedorRepository = desenvolvedorRepository;
        this.jwt = jwt;
    }

    public void cadastrarTarefa(Tarefa tarefa) {
        Long desenvolvedor = desenvolvedorRepository.getById(tarefa.getDesenvolvedor().getId()).getId();
        if (desenvolvedor == null) {
            throw new RuntimeException("Desenvolvedor não encontrado");
        }

        this.tarefaRepository.save(tarefa);
    }

    public List<TarefaDTO> buscarTarefaPorDev(HttpServletRequest request){
        String login = this.jwt.extractUsername(this.jwt.trataToken(request));
        List<Tarefa> tarefas = this.tarefaRepository.buscarTarefaPorDev(login);
        List<TarefaDTO> tarefasDTO = new ArrayList<>();

        for(Tarefa tarefa : tarefas){
            tarefasDTO.add(new TarefaDTO(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(),
                    tarefa.getStatus()));
        }
        return tarefasDTO;
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
