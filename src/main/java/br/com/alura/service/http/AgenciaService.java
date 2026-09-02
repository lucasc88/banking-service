package br.com.alura.service.http;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.alura.domain.Agencia;
import br.com.alura.exceptions.AgenciaNaoAtivaOuNaoCadastradaException;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AgenciaService {

    @RestClient
    private SituacaoCadastralHttpService situacaoCadastralHttpService;

    private List<Agencia> agencias = new ArrayList<>();

    public void cadastrarAgencia(Agencia a) {
        AgenciaHttp agencia = this.situacaoCadastralHttpService.buscarPorCnpj(a.getCnpj());
        if (agencia != null && agencia.getSituacaoCadastral().equals(SituacaoCadastral.ATIVO)) {
            agencias.add(a);
        } else {
            throw new AgenciaNaoAtivaOuNaoCadastradaException();
        }
    }

    public Agencia buscarPorId(Integer id) {
        return agencias.stream().filter(a -> a.getId().equals(id)).toList().getFirst();
    }

    public void remover(Integer id) {
        agencias.removeIf(a -> a.getId().equals(id));
    }

    public void alterar(Agencia a) {
        remover(a.getId());
        cadastrarAgencia(a);
    }
}
