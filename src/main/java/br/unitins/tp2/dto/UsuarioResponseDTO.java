package br.unitins.tp2.dto;

import br.unitins.tp2.model.Perfil;
import br.unitins.tp2.model.Usuario;

public record UsuarioResponseDTO(Long id, String username, String senha, Perfil perfil) {

    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        return new UsuarioResponseDTO (
            usuario.getId(), 
            usuario.getUsername(), 
            usuario.getSenha(),
            usuario.getPerfil());
    }
    
}