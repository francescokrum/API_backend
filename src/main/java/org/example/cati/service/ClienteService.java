package org.example.cati.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.cati.infra.security.config.JwtServiceGenerator;
import org.example.cati.model.cliente.Cliente;
import org.example.cati.model.cliente.dto.ClienteDTO;
import org.example.cati.model.cliente.repositories.ClienteRepository;
import org.example.cati.model.unidade.UnidadeDeNegocio;
import org.example.cati.model.unidade.repositories.UnidadeDeNegocioRepository;
import org.example.cati.enums.permissao.Permissao;
import org.example.cati.model.usuario.UsuarioDTO;
import org.example.cati.model.usuario.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {

    private final ClienteRepository repository;
    private final UnidadeDeNegocioRepository unidadeRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    private final JwtServiceGenerator jwt;

    @Autowired
    public ClienteService(ClienteRepository repository, UnidadeDeNegocioRepository unidadeRepository, PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository, JwtServiceGenerator jwt) {
        this.repository = repository;
        this.unidadeRepository = unidadeRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
        this.jwt = jwt;
    }

    public void cadastrarCliente(Cliente cliente) throws Exception {
        // Codificar a senha usando BCrypt
        String senhaCodificada = passwordEncoder.encode(cliente.getSenha());
        cliente.setSenha(senhaCodificada);

        if (cliente.getPermissao() == null) {
            cliente.setPermissao(Permissao.CLIENTE);
        }

        Optional<UnidadeDeNegocio> optionalUnidade = unidadeRepository.findByCnpj(cliente.getCnpj_unidade());
        if (optionalUnidade.isPresent()) {
            UnidadeDeNegocio unidade = optionalUnidade.get();
            cliente.setUnidadeDeNegocio(unidade);
            this.repository.save(cliente);
        } else {
            throw new Exception("CNPJ não existente");
        }
    }

    public List<UsuarioDTO> listarUsuarios(){
        return this.usuarioRepository.findAllBy();
    }

    public List<Cliente> buscarClientes() {
        return this.repository.findAllBy();
    }

    public ClienteDTO buscarClientePorId(HttpServletRequest request) {

        String login = this.jwt.extractUsername(this.jwt.trataToken(request));
        Cliente usuario = this.repository.findByLogin(login);

        return new ClienteDTO(usuario.getNome(), usuario.getCpf(), usuario.getEmail(),
                usuario.getLogin(), usuario.getSenha(), usuario.getCnpj_unidade());

    }

    public void removerCliente(Long id) {
        this.repository.deleteById(id);
    }

    public void editarCliente(ClienteDTO cliente, HttpServletRequest request) {

        String login = this.jwt.extractUsername(this.jwt.trataToken(request));
        Cliente usuario = this.repository.findByLogin(login);

        usuario.setNome(cliente.nome());
        usuario.setCpf(cliente.cpf());
        usuario.setEmail(cliente.email());
        usuario.setLogin(cliente.login());
        usuario.setCnpj_unidade(cliente.cnpj_unidade());

            /*if (!usuario.getCnpj_unidade().equals(cliente.cnpj_unidade())) {
                usuario.setCnpj_unidade(cliente.cnpj_unidade());

                Optional<UnidadeDeNegocio> novaUnidade = unidadeRepository.findByCnpj(cliente.cnpj_unidade());

                if (novaUnidade.isPresent()) {
                    usuario.setUnidadeDeNegocio(novaUnidade.get());
                } else {
                    throw new RuntimeException("Unidade de negócio com o CNPJ fornecido não encontrada.");
                }
            }*/

            this.repository.save(usuario);
    }
}
