package org.example.cati.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.cati.enums.status.StatusChamado;
import org.example.cati.infra.security.config.JwtServiceGenerator;
import org.example.cati.model.chamado.Chamado;
import org.example.cati.model.chamado.dto.ChamadoDTO;
import org.example.cati.model.chamado.repositories.ChamadoRepository;
import org.example.cati.model.cliente.Cliente;
import org.example.cati.model.cliente.repositories.ClienteRepository;
import org.example.cati.model.produto.Produto;
import org.example.cati.model.produto.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final JwtServiceGenerator jwt;

    public ChamadoService(ChamadoRepository chamadoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository, JwtServiceGenerator jwt) {
        this.chamadoRepository = chamadoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.jwt = jwt;
    }

    public void cadastrarChamado(Chamado chamado, HttpServletRequest request) {

        String login = this.jwt.extractUsername(this.jwt.trataToken(request));
        Cliente usuario = this.clienteRepository.findByLogin(login);

        chamado.setCliente(usuario);

        if(chamado.getStatus() == null){
            chamado.setStatus(StatusChamado.SOLICITADO);
        }

        Optional<Produto> produto = produtoRepository.findById(chamado.getProduto().getId());
        if (!produto.isPresent()) {
            throw new RuntimeException("Produto não encontrado");
        }

        this.chamadoRepository.save(chamado);
    }

    public List<ChamadoDTO> buscarChamados() {
        return this.chamadoRepository.findAllBy();
    }

    public List<ChamadoDTO> buscarChamadosPorCliente(HttpServletRequest request) {

        String login = this.jwt.extractUsername(this.jwt.trataToken(request));
        List<Chamado> chamados = this.chamadoRepository.findAllByCliente(login);
        List<ChamadoDTO> chamadosDTO = new ArrayList<>();

        for (Chamado chamado : chamados) {
            chamadosDTO.add(new ChamadoDTO(chamado.getId(), chamado.getTitulo(), chamado.getDescricao(), chamado.getStatus(),
                    chamado.getGravidade()));

        }

        return chamadosDTO;
    }

    public Chamado buscarChamadoPorId(Long id) {
        return this.chamadoRepository.getById(id);
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

