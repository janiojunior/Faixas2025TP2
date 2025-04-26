package br.unitins.tp2.service;

import java.util.List;

import br.unitins.tp2.dto.UsuarioDTO;
import br.unitins.tp2.dto.UsuarioResponseDTO;
import br.unitins.tp2.model.Perfil;
import br.unitins.tp2.model.Usuario;
import br.unitins.tp2.repository.UsuarioRepository;
import br.unitins.tp2.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Override
    @Transactional
    public UsuarioResponseDTO insert(UsuarioDTO dto) {
        if (repository.findByUsername(dto.username()) != null) {
            throw new ValidationException("username", "O username informado já existe, informe outro username.");
        }
        Usuario novoUsuario = new Usuario();
        novoUsuario.setUsername(dto.username());
        novoUsuario.setSenha(dto.senha());
        novoUsuario.setPerfil(Perfil.valueOf(dto.idPerfil()));

        repository.persist(novoUsuario);

        return UsuarioResponseDTO.valueOf(novoUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(UsuarioDTO dto, Long id) {
        return null;

    }

    @Override
    @Transactional
    public void delete(Long id) {
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
         return null;
    }

    @Override
    public List<UsuarioResponseDTO> findByNome(String nome) {
             return null;
    }

    @Override
    public List<UsuarioResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(e -> UsuarioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public UsuarioResponseDTO findByLoginAndSenha(String login, String senha) {
        Usuario usuario = repository.findByUsernameAndSenha(login, senha);
        return UsuarioResponseDTO.valueOf(usuario);
    }
    
}
