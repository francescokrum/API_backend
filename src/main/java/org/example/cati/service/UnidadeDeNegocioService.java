package org.example.cati.service;

import org.example.cati.model.unidade.UnidadeDeNegocio;
import org.example.cati.model.unidade.dto.UnidadeDeNegocioDTO;
import org.example.cati.model.unidade.repositories.UnidadeDeNegocioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeDeNegocioService {

    private final UnidadeDeNegocioRepository repository;

    public UnidadeDeNegocioService(UnidadeDeNegocioRepository repository) {
        this.repository = repository;
    }

    public void cadastrarUnidadeDeNegocio(UnidadeDeNegocio unidadeDeNegocio) {
        this.repository.save(unidadeDeNegocio);
    }

    public List<UnidadeDeNegocioDTO> buscarUnidadeDeNegocio() {

        return this.repository.findAllBy();
    }

    public UnidadeDeNegocioDTO buscarUnidadeDeNegocioPorId(Long id) {
        return this.repository.findById(id).get();
    }

    public void removerUnidadeDeNegocio(Long id) {
        this.repository.deleteById(id);
    }

    public void editarUnidadeDeNegocio(UnidadeDeNegocio unidadeDeNegocio) {

        UnidadeDeNegocio unidade = this.repository.getReferenceById(unidadeDeNegocio.getId());

        unidade.setNome(unidadeDeNegocio.getNome());
        unidade.setCnpj(unidadeDeNegocio.getCnpj());

        this.repository.save(unidadeDeNegocio);
    }
}
