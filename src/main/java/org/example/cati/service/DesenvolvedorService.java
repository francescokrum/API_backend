package org.example.cati.service;

import org.example.cati.model.desenvolvedor.Desenvolvedor;
import org.example.cati.model.desenvolvedor.dto.DesenvolvedorDTO;
import org.example.cati.model.desenvolvedor.repositories.DesenvolvedorRepository;
import org.example.cati.enums.permissao.Permissao;
import org.example.cati.model.usuario.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesenvolvedorService {

    private final DesenvolvedorRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DesenvolvedorService(DesenvolvedorRepository repository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void cadastrarDev(Desenvolvedor desenvolvedor) {

        String senhaCodificada = passwordEncoder.encode(desenvolvedor.getSenha());
        desenvolvedor.setSenha(senhaCodificada);

        // Definir a permissão como CLIENTE se não estiver definida
        if (desenvolvedor.getPermissao() == null) {
            desenvolvedor.setPermissao(Permissao.DEV);
        }

        if (repository.existsByCpf(desenvolvedor.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        if (repository.existsByEmail(desenvolvedor.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        if (repository.existsByLogin(desenvolvedor.getLogin())) {
            throw new RuntimeException("Login já cadastrado");
        }

        this.repository.save(desenvolvedor);
    }

    public List<DesenvolvedorDTO> buscarDevs(){
        return this.repository.findAllBy();
    }

    public Desenvolvedor buscarDevPorId(Long id) {
        return this.repository.findById(id).get();
    }

    public void removerDev(Long id) {
        this.repository.deleteById(id);
    }

    public void editarDev(Desenvolvedor desenvolvedor) {

        Desenvolvedor dev = this.repository.getReferenceById(desenvolvedor.getId());

        String senhaCodificada = passwordEncoder.encode(desenvolvedor.getSenha());
        desenvolvedor.setSenha(senhaCodificada);

        dev.setNome(desenvolvedor.getNome());
        dev.setCpf(desenvolvedor.getCpf());
        dev.setEmail(desenvolvedor.getEmail());
        dev.setPermissao(desenvolvedor.getPermissao());
        dev.setLogin(desenvolvedor.getLogin());
        dev.setSenha(senhaCodificada);
        dev.setDataNasc(desenvolvedor.getDataNasc());
        dev.setSalario(desenvolvedor.getSalario());
        dev.setCargo(desenvolvedor.getCargo());

        this.repository.save(dev);
    }
}
