package br.org.fepb.api.web.rest;

import br.org.fepb.api.domain.Cidade;
import br.org.fepb.api.domain.Estado;
import br.org.fepb.api.service.EnderecoService;
import br.org.fepb.api.service.dto.ConfiguracaoEventoDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoResource {

    private EnderecoService enderecoService;

    public EnderecoResource(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/estados")
    public List<Estado> listarEstados() {
        return this.enderecoService.listarEstados();
    }

    @GetMapping("/cidades/{id}")
    public List<Cidade> listarCidades(@PathVariable Long id) {
        Estado e = this.enderecoService.buscarEstadoPorId(id);
        if (e != null) {
            return this.enderecoService.listarCidadesPorEstado(e);
        }
        return null;
    }

}
