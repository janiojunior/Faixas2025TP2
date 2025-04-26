package br.unitins.tp2.dto;

import br.unitins.tp2.model.Estado;

public record EstadoResponseDTO (
    Long id,
    String nome,
    String sigla
) {
    public static EstadoResponseDTO valueOf(Estado estado) {
        return new EstadoResponseDTO(
            estado.getId(), 
            estado.getNome(), 
            estado.getSigla());
    }
    
}
