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
        // Obtém o cliente existente pelo ID
        Cliente cli = this.repository.getReferenceById(cliente.getId());

        // Atualiza os campos do cliente com os valores fornecidos
        cli.setNome(cliente.getNome());
        cli.setCpf(cliente.getCpf());
        cli.setEmail(cliente.getEmail());
        cli.setPermissao(cliente.getPermissao());
        cli.setLogin(cliente.getLogin());
        cli.setSenha(cliente.getSenha());

        // Verifica se o CNPJ da unidade de negócio foi alterado
        if (!cli.getCnpj_unidade().equals(cliente.getCnpj_unidade())) {
            // Atualiza o CNPJ do cliente
            cli.setCnpj_unidade(cliente.getCnpj_unidade());

            // Obtém a nova unidade de negócio com base no CNPJ fornecido
            Optional<UnidadeDeNegocio> novaUnidade = unidadeRepository.findByCnpj(cliente.getCnpj_unidade());

            // Verifica se a nova unidade de negócio existe
            if (novaUnidade.isPresent()) {
                // Define o ID da nova unidade de negócio no cliente
                cli.setUnidadeDeNegocio(novaUnidade.get());
            } else {
                throw new RuntimeException("Unidade de negócio com o CNPJ fornecido não encontrada.");
            }
        }

        // Salva o cliente atualizado no banco de dados
        this.repository.save(cli);
    }
}
