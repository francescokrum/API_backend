package org.example.cati.service;

import org.example.cati.model.cliente.Cliente;
import org.example.cati.model.cliente.dto.ClienteDTO;
import org.example.cati.model.cliente.repositories.ClienteRepository;
import org.example.cati.model.unidade.UnidadeDeNegocio;
import org.example.cati.model.unidade.repositories.UnidadeDeNegocioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final UnidadeDeNegocioRepository unidadeRepository;

    public ClienteService(ClienteRepository repository, UnidadeDeNegocioRepository unidadeRepository) {
        this.repository = repository;
        this.unidadeRepository = unidadeRepository;
    }

    public void cadastrarCliente(Cliente cliente) throws Exception {
        Optional<UnidadeDeNegocio> optionalUnidade = unidadeRepository.findByCnpj(cliente.getCnpj_unidade());
        if (optionalUnidade.isPresent()) {
            UnidadeDeNegocio unidade = optionalUnidade.get();
            cliente.setUnidadeDeNegocio(unidade); // Associa a unidade de negócio ao cliente
            this.repository.save(cliente);
        } else {
            throw new Exception("CNPJ não existente");
        }
    }

    public List<ClienteDTO> buscarClientes() {
        return this.repository.findAllBy();
    }

    public ClienteDTO buscarClientePorId(Long id) {
        return this.repository.findById(id).get();
    }

    public void removerCliente(Long id) {
        this.repository.deleteById(id);
    }

    public void editarCliente(Cliente cliente) {
        Cliente cli = this.repository.getReferenceById(cliente.getId());

        cli.setNome(cliente.getNome());
        cli.setCpf(cliente.getCpf());
        cli.setEmail(cliente.getEmail());
        cli.setPermissao(cliente.getPermissao());
        cli.setLogin(cliente.getLogin());
        cli.setSenha(cliente.getSenha());

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
