package org.example.cati.service;

import org.example.cati.model.unidade.UnidadeDeNegocio;
import org.example.cati.model.unidade.dto.UnidadeDTO;
import org.example.cati.model.unidade.repositories.UnidadeDeNegocioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadeDeNegocioService {

    private final UnidadeDeNegocioRepository repository;

    public UnidadeDeNegocioService(UnidadeDeNegocioRepository repository) {
        this.repository = repository;
    }

    public void cadastrarUnidadeDeNegocio(UnidadeDeNegocio unidadeDeNegocio) {
        this.repository.save(unidadeDeNegocio);
    }

    public List<UnidadeDTO> buscarUnidadeDeNegocio() {

        return this.repository.findAllBy();
    }

    public Optional<UnidadeDeNegocio> buscarUnidadeDeNegocioPorId(Long id) {
        return this.repository.findById(id);
    }

    public void removerUnidadeDeNegocio(Long id) {
        this.repository.deleteById(id);
    }

    public void editarUnidadeDeNegocio(UnidadeDeNegocio unidadeDeNegocio) {

        UnidadeDeNegocio unidade = (UnidadeDeNegocio) this.repository.getReferenceById(unidadeDeNegocio.getId());

        unidade.setNome(unidadeDeNegocio.getNome());
        unidade.setCnpj(unidadeDeNegocio.getCnpj());

        this.repository.save(unidadeDeNegocio);
    }
}
