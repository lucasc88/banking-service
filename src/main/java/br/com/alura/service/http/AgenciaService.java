package br.com.alura.service.http;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.alura.domain.Agencia;
import br.com.alura.exceptions.AgenciaNaoAtivaOuNaoCadastradaException;
import br.com.alura.repository.AgenciaRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AgenciaService {

    // it's mandatory to inject a RestClient by annotation. It's not allowed to
    // inject by constructor
    @RestClient
    private SituacaoCadastralHttpService situacaoCadastralHttpService;

    private final AgenciaRepository agenciaRepository;

    AgenciaService(AgenciaRepository agenciaRepository) {
        this.agenciaRepository = agenciaRepository;
    }

    public void cadastrarAgencia(Agencia a) {
        AgenciaHttp agencia = this.situacaoCadastralHttpService.buscarPorCnpj(a.getCnpj());
        if (agencia != null && agencia.getSituacaoCadastral().equals(SituacaoCadastral.ATIVO)) {
            agenciaRepository.persist(a);
        } else {
            throw new AgenciaNaoAtivaOuNaoCadastradaException();
        }
    }

    public Agencia buscarPorId(Long id) {
        return agenciaRepository.findById(id);
    }

    public void remover(Long id) {
        agenciaRepository.delete(agenciaRepository.findById(id));

    }

    public void alterar(Agencia a) {
        agenciaRepository.update("nome = ?1, razaoSocial = ?2, cnpj = ?3 where id = ?4", a.getNome(), a.getRazaoSocial(), a.getCnpj(), a.getId());
    }
}
