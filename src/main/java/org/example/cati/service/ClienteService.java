package org.example.cati.service;

import org.example.cati.model.cliente.Cliente;
import org.example.cati.model.cliente.dto.ClienteDTO;
import org.example.cati.model.cliente.repositories.ClienteRepository;
import org.example.cati.model.unidade.UnidadeDeNegocio;
import org.example.cati.model.unidade.repositories.UnidadeDeNegocioRepository;
import org.example.cati.enums.permissao.Permissao;
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

    @Autowired
    public ClienteService(ClienteRepository repository, UnidadeDeNegocioRepository unidadeRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.unidadeRepository = unidadeRepository;
        this.passwordEncoder = passwordEncoder;
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

    public List<ClienteDTO> buscarClientes() {
        return this.repository.findAllBy();
    }

    public ClienteDTO buscarClientePorId(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    public void removerCliente(Long id) {
        this.repository.deleteById(id);
    }

    public void editarCliente(Cliente cliente) {

        Cliente cli = this.repository.findById(cliente.getId()).orElse(null);

        String senhaCodificada = passwordEncoder.encode(cliente.getSenha());
        cliente.setSenha(senhaCodificada);

            cli.setNome(cliente.getNome());
            cli.setCpf(cliente.getCpf());
            cli.setEmail(cliente.getEmail());
            cli.setPermissao(cliente.getPermissao());
            cli.setLogin(cliente.getLogin());
            cli.setSenha(senhaCodificada);

            // Verificar se a unidade de negócio foi alterada
            if (!cli.getCnpj_unidade().equals(cliente.getCnpj_unidade())) {
                cli.setCnpj_unidade(cliente.getCnpj_unidade());

                Optional<UnidadeDeNegocio> novaUnidade = unidadeRepository.findByCnpj(cliente.getCnpj_unidade());

                if (novaUnidade.isPresent()) {
                    cli.setUnidadeDeNegocio(novaUnidade.get());
                } else {
                    throw new RuntimeException("Unidade de negócio com o CNPJ fornecido não encontrada.");
                }
            }

            this.repository.save(cli);
    }
}
