package org.example.cati.service;

import org.example.cati.model.chamado.Chamado;
import org.example.cati.model.chamado.dto.ChamadoDTO;
import org.example.cati.model.chamado.repositories.ChamadoRepository;
import org.example.cati.model.cliente.Cliente;
import org.example.cati.model.cliente.repositories.ClienteRepository;
import org.example.cati.model.produto.Produto;
import org.example.cati.model.produto.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public ChamadoService(ChamadoRepository chamadoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.chamadoRepository = chamadoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public void cadastrarChamado(Chamado chamado) {
        Long idUsuario = chamado.getCliente().getId();
        Cliente cliente = clienteRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado para o id_usuario: " + idUsuario));

        Optional<Produto> produto = produtoRepository.findById(chamado.getProduto().getId());
        if (!produto.isPresent()) {
            throw new RuntimeException("Produto não encontrado");
        }

        this.chamadoRepository.save(chamado);
    }

    public List<ChamadoDTO> buscarChamados() {
        return this.chamadoRepository.findAllBy();
    }

    public ChamadoDTO buscarChamadoPorId(Long id) {
        return this.chamadoRepository.findById(id).get();
    }

    public void removerChamado(Long id) {
        this.chamadoRepository.deleteById(id);
    }

    public void editarChamado(Chamado chamado) {
        Chamado chamadoExistente = this.chamadoRepository.getReferenceById(chamado.getId());

        chamadoExistente.setTitulo(chamado.getTitulo());
        chamadoExistente.setDescricao(chamado.getDescricao());
        chamadoExistente.setStatus(chamado.getStatus());
        chamadoExistente.setGravidade(chamado.getGravidade());

        Optional<Cliente> cliente = clienteRepository.findById(chamado.getCliente().getId());
        if (!cliente.isPresent()) {
            throw new RuntimeException("Cliente não encontrado");
        }
        chamadoExistente.setCliente(cliente.get());

        Optional<Produto> produto = produtoRepository.findById(chamado.getProduto().getId());
        if (!produto.isPresent()) {
            throw new RuntimeException("Produto não encontrado");
        }
        chamadoExistente.setProduto(produto.get());

        this.chamadoRepository.save(chamadoExistente);
    }
}

