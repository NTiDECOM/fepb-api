package br.org.fepb.api.web.rest;

import br.org.fepb.api.domain.HistoricoContribuicao;
import br.org.fepb.api.domain.Socio;
import br.org.fepb.api.service.SocioService;
import br.org.fepb.api.service.dto.HistoricoContribuicaoDTO;
import br.org.fepb.api.service.dto.SocioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/socios")
public class SocioResource {

    @Autowired
    private SocioService socioService;

    @GetMapping
    public List<Socio> listaSocios() {
        return this.socioService.listarSocios();
    }

    @GetMapping("/{id}")
    public Socio buscarPorId(@PathVariable Long id) {
        return this.socioService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Socio salvarSocio(@RequestBody SocioDTO s) throws ParseException {
        return this.socioService.salvarSocio(s);
    }

    @PostMapping("/contribuicao")
    @ResponseStatus(HttpStatus.CREATED)
    public HistoricoContribuicao salvarContribuicao(@RequestBody HistoricoContribuicaoDTO h) throws ParseException {
        return this.socioService.salvarContribuicao(h);
    }

    @GetMapping("/{id}/contribuicao")
    @ResponseStatus(HttpStatus.CREATED)
    public List<HistoricoContribuicao> pegarContribuicao(@PathVariable Long id) {

        Socio s = socioService.buscarPorId(id);
        return this.socioService.buscarContribuicaoPorSocio(s);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Socio updateSocio(@RequestBody SocioDTO s, @PathVariable Long id) throws Exception {
        return this.socioService.atualizarSocio(s);
    }

}
