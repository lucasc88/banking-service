package br.com.alura.controller;

import org.jboss.resteasy.reactive.RestResponse;

import br.com.alura.domain.Agencia;
import br.com.alura.service.http.AgenciaService;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;

@Path("/agencias")
public class AgenciaController {

    // AgenciaService can be inject by AgenciaController Constructor
    private AgenciaService agenciaService;
    AgenciaController(AgenciaService a) {
        this.agenciaService = a;
    }

    // OR injected by @Inject annotation
    // @Inject
    // private final AgenciaService agenciaService;

    @POST
    public RestResponse<Void> cadastrar(Agencia a, @Context UriInfo uriInfo) {
        this.agenciaService.cadastrarAgencia(a);
        return RestResponse.created(uriInfo.getAbsolutePath());
    }

    @GET
    @Path("{id}")
    public RestResponse<Agencia> buscarPorId(Integer id) {
        return RestResponse.ok(this.agenciaService.buscarPorId(id));
    }

    @DELETE
    @Path("{id}")
    public RestResponse<Void> remover(Integer id) {
        this.agenciaService.remover(id);
        return RestResponse.ok();
    }

    @PUT
    public RestResponse<Void> alterar(Agencia a) {
        this.agenciaService.alterar(a);
        return RestResponse.ok();
    }
}
