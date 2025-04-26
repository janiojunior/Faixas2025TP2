package br.unitins.tp2.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.tp2.dto.FaixaDTO;
import br.unitins.tp2.dto.FaixaResponseDTO;
import br.unitins.tp2.model.Faixa;
import br.unitins.tp2.model.Modalidade;
import br.unitins.tp2.repository.FaixaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class FaixaServiceImpl implements FaixaService {

    @Inject
    FaixaRepository faixaRepository;

    @Inject
    Validator validator;

    @Override
    public List<FaixaResponseDTO> getAll(int page, int pageSize) {

        List<Faixa> list = faixaRepository.findAll().page(page, pageSize).list();
        return list.stream().map(e -> FaixaResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public FaixaResponseDTO findById(Long id) {
        Faixa faixa = faixaRepository.findById(id);
        if (faixa == null)
            throw new NotFoundException("Faixa não encontrada.");
        return FaixaResponseDTO.valueOf(faixa);
    }

    @Override
    @Transactional
    public FaixaResponseDTO create(@Valid FaixaDTO faixaDTO) throws ConstraintViolationException {
        Faixa entity = new Faixa();
        entity.setNome(faixaDTO.nome());
        entity.setDescricao(faixaDTO.descricao());
        entity.setPreco(faixaDTO.preco());
        entity.setEstoque(faixaDTO.estoque());
        entity.setModalidade(Modalidade.valueOf(faixaDTO.idModalidade()));

        faixaRepository.persist(entity);

        return FaixaResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public FaixaResponseDTO update(Long id, FaixaDTO faixaDTO) throws ConstraintViolationException {
        validar(faixaDTO);

        Faixa entity = faixaRepository.findById(id);

        entity.setNome(faixaDTO.nome());
        entity.setDescricao(faixaDTO.descricao());
        entity.setPreco(faixaDTO.preco());
        entity.setEstoque(faixaDTO.estoque());
        entity.setModalidade(Modalidade.valueOf(faixaDTO.idModalidade()));

        return FaixaResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public FaixaResponseDTO salveImage(Long id, String nomeImagem) {

        Faixa entity = faixaRepository.findById(id);
        entity.setNomeImagem(nomeImagem);

        return FaixaResponseDTO.valueOf(entity);
    }

    private void validar(FaixaDTO faixaDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<FaixaDTO>> violations = validator.validate(faixaDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        faixaRepository.deleteById(id);
    }

    @Override
    public List<FaixaResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Faixa> list = faixaRepository.findByNome(nome).page(page, pageSize).list();
        return list.stream().map(e -> FaixaResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return faixaRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return faixaRepository.findByNome(nome).count();
    }

    
}
