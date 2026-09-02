package br.com.alura.service.http;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

// Quarkus just use this interface without its implementation
// RegisterRestClient to be identified as a Rest Client
@Path("/situacao-cadastral")
@RegisterRestClient(configKey = "situacao-cadastral-api") // configKey is defined in application.properties
public interface SituacaoCadastralHttpService {

    @GET
    @Path("/{cnpj}")
    AgenciaHttp buscarPorCnpj(@PathParam("cnpj") String cnpj);
}
