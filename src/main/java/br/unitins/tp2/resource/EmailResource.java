package br.unitins.tp2.resource;

import br.unitins.tp2.service.EmailService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/email")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmailResource {

    @Inject
    EmailService emailService;

    @POST
    public Response enviarEmail() {
       emailService.enviarEmail("janiojunior@gmail.com", 
                                "Teste de Topicos", 
                                "teste de email");

        return Response.ok("Deu certo").build();
        
    }
  
}