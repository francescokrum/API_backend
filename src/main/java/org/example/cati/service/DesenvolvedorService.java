package org.example.cati.service;

import org.example.cati.model.desenvolvedor.Desenvolvedor;
import org.example.cati.model.desenvolvedor.dto.DesenvolvedorDTO;
import org.example.cati.model.desenvolvedor.repositories.DesenvolvedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesenvolvedorService {

    private final DesenvolvedorRepository repository;

    public DesenvolvedorService(DesenvolvedorRepository repository) {
        this.repository = repository;
    }

    public void cadastrarDev(Desenvolvedor desenvolvedor) {
        this.repository.save(desenvolvedor);
    }

    public List<DesenvolvedorDTO> listarDevs(){
        return this.repository.findAllBy();
    }

    public Desenvolvedor buscarDev(Long id) {
        return this.repository.getById(id);
    }

    public void removerDev(Long id) {
        this.repository.deleteById(id);
    }

    public void editarDev(Desenvolvedor desenvolvedor) {

        Desenvolvedor dev = this.repository.getReferenceById(desenvolvedor.getId());

        dev.setNome(desenvolvedor.getNome());
        dev.setCpf(desenvolvedor.getCpf());
        dev.setEmail(desenvolvedor.getEmail());
        dev.setPermissao(desenvolvedor.getPermissao());
        dev.setLogin(desenvolvedor.getLogin());
        dev.setSenha(desenvolvedor.getSenha());
        dev.setDataNasc(desenvolvedor.getDataNasc());
        dev.setSalario(desenvolvedor.getSalario());
        dev.setCargo(desenvolvedor.getCargo());

        this.repository.save(dev);
    }
}
