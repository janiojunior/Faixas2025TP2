package br.unitins.tp2.service;

import java.util.List;

import br.unitins.tp2.dto.MunicipioDTO;
import br.unitins.tp2.dto.MunicipioResponseDTO;


public interface MunicipioService {

        // recursos basicos
        List<MunicipioResponseDTO> getAll();

        MunicipioResponseDTO findById(Long id);
    
        MunicipioResponseDTO create(MunicipioDTO dto);
    
        MunicipioResponseDTO update(Long id, MunicipioDTO dto);
    
        void delete(Long id);
    
        // recursos extras
    
        List<MunicipioResponseDTO> findByNome(String nome);
    
        long count();
    
}