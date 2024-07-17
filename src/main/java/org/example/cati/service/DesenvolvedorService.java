package org.example.cati.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.cati.infra.security.config.JwtServiceGenerator;
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
    private final JwtServiceGenerator jwt;

    public DesenvolvedorService(DesenvolvedorRepository repository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtServiceGenerator jwt) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwt = jwt;
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

    public DesenvolvedorDTO buscarDevPorId(HttpServletRequest request) {
        String login = this.jwt.extractUsername(this.jwt.trataToken(request));
        Desenvolvedor usuario = this.repository.findByLogin(login);

        return new DesenvolvedorDTO(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getEmail(), usuario.getPermissao(),
                usuario.getLogin(), usuario.getSenha(), usuario.getDataNasc(), usuario.getSalario(), usuario.getCargo());
    }

    public void removerDev(Long id) {
        this.repository.deleteById(id);
    }

    public void editarDev(DesenvolvedorDTO desenvolvedor, HttpServletRequest request) {
        String login = jwt.extractUsername(jwt.trataToken(request));
        Desenvolvedor usuario = repository.findByLogin(login);

        // Verifica se a senha no DTO não é nula antes de codificar
        if (desenvolvedor.senha() != null) {
            String senhaCodificada = passwordEncoder.encode(desenvolvedor.senha());
            usuario.setSenha(senhaCodificada);
        }

        // Atribui os demais atributos do DTO ao usuário
        usuario.setNome(desenvolvedor.nome());
        usuario.setCpf(desenvolvedor.cpf());
        usuario.setEmail(desenvolvedor.email());
        usuario.setLogin(desenvolvedor.login());

        repository.save(usuario);
    }
}
