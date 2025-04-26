package br.unitins.tp2.service;

import br.unitins.tp2.dto.UsuarioResponseDTO;

public interface JwtService {
    public String generateJwt(UsuarioResponseDTO dto);
}
