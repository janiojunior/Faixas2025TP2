package br.unitins.tp2.resource;

import java.util.List;

import br.unitins.tp2.dto.EstadoDTO;
import br.unitins.tp2.model.Estado;
import br.unitins.tp2.service.EstadoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("estados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstadoResource {

    @Inject
    EstadoService service;

    @GET
    @RolesAllowed("Adm")
    public List<Estado> buscarTodos(@QueryParam("page") @DefaultValue("0") int page,
                                    @QueryParam("page_size") @DefaultValue("100") int pageSize) { 
        return service.findAll(page, pageSize);
    }

    @GET
    @Path("/nome/{nome}")
    public List<Estado> buscarPorNome(@PathParam("id") String nome, @QueryParam("page") @DefaultValue("0") int page,
                                    @QueryParam("page_size") @DefaultValue("100") int pageSize) { 
        return service.findByNome(nome, page, pageSize);
    }

    @GET
    @Path("/{id}")
    public Estado buscarPorId(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/sigla/{sigla}")
    public Estado buscarPorSigla(@PathParam("id") String sigla) { 
        return service.findBySigla(sigla);
    }

    @POST
    public Estado incluir(@Valid EstadoDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public void alterar(@PathParam("id") Long id, Estado estado) {
        service.update(id, estado);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void apagar(@PathParam("id") Long id) {
        service.delete(id);
    }

    @GET
    @Path("/count")
    public long total() {
        return service.count();
    }

    @GET
    @Path("/nome/{nome}/count")
    public long totalPorNome(@PathParam("id") String nome) {
        return service.count(nome);
    }



}
