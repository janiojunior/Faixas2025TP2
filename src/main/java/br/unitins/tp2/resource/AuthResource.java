package br.unitins.tp2.resource;

import br.unitins.tp2.dto.AuthUsuarioDTO;
import br.unitins.tp2.dto.UsuarioResponseDTO;
import br.unitins.tp2.service.HashService;
import br.unitins.tp2.service.JwtService;
import br.unitins.tp2.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    JwtService jwtService;

    @POST
    public Response login(AuthUsuarioDTO authDTO) {
        String hash = hashService.getHashSenha(authDTO.senha());

        UsuarioResponseDTO usuario = usuarioService.findByLoginAndSenha(authDTO.login(), hash);

        if (usuario == null) {
            return Response.status(Status.NOT_FOUND)
                .entity("Usuario não encontrado").build();
        } 
        return Response.ok(usuario)
            .header("Authorization", jwtService.generateJwt(usuario))
            .build();
        
    }
  
}