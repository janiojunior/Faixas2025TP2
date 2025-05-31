package br.unitins.tp2.resource;

import java.io.IOException;
import java.util.List;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.tp2.dto.FaixaDTO;
import br.unitins.tp2.dto.FaixaResponseDTO;
import br.unitins.tp2.form.FaixaImageForm;
import br.unitins.tp2.model.Modalidade;
import br.unitins.tp2.service.FaixaService;
import br.unitins.tp2.service.FileService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Path("/faixas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FaixaResource {

    @Inject
    FaixaService faixaService;

    @Inject
    FileService fileService;

    private static final Logger LOG = Logger.getLogger(FaixaResource.class);

    @GET
    @RolesAllowed("blah")
    public List<FaixaResponseDTO> getAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

        LOG.info("Buscando todos os faixas.");
        LOG.debug("ERRO DE DEBUG.");
        return faixaService.getAll(page, pageSize);
    }

    @GET
    @Path("/{id}")
    public FaixaResponseDTO findById(@PathParam("id") Long id) {
        return faixaService.findById(id);
    }

    @POST
    public Response insert(FaixaDTO dto) {
        LOG.infof("Inserindo um faixa: %s", dto.nome());

        FaixaResponseDTO faixa = faixaService.create(dto);
        LOG.infof("Faixa (%d) criado com sucesso.", faixa.id());
        return Response.status(Status.CREATED).entity(faixa).build();

    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, FaixaDTO dto) {
        try {
            FaixaResponseDTO faixa = faixaService.update(id, dto);
            return Response.ok(faixa).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        faixaService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public long count() {
        return faixaService.count();
    }

    @GET
    @Path("/search/{nome}/count")
    public long count(@PathParam("nome") String nome) {
        return faixaService.countByNome(nome);
    }

    @GET
    @Path("/search/{nome}")
    public List<FaixaResponseDTO> search(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        return faixaService.findByNome(nome, page, pageSize);

    }

    @GET
    @Path("/image/download/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = Response.ok(fileService.download(nomeImagem));
        response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
        return response.build();
    }

    @PATCH
    @Path("/image/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm FaixaImageForm form) {

        try {
            fileService.salvar(form.getId(), form.getNomeImagem(), form.getImagem());
            return Response.noContent().build();
        } catch (IOException e) {
            return Response.status(Status.CONFLICT).build();
        }

    }

    @GET
    @Path("/modalidades")
    public Response getModalidades() {
        return Response.ok(Modalidade.values()).build();
    }

}
