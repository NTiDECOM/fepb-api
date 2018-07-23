package br.org.fepb.api.web.rest;

import br.org.fepb.api.domain.Inscricao;
import br.org.fepb.api.domain.Oficina;
import br.org.fepb.api.repository.InscricaoRepository;
import br.org.fepb.api.security.AuthoritiesConstants;
import br.org.fepb.api.service.InscricaoService;
import br.org.fepb.api.service.MailService;
import br.org.fepb.api.service.OficinaService;
import br.org.fepb.api.service.dto.InscricaoDTO;
import br.org.fepb.api.service.dto.OficinaDTO;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/aje")
public class AjeResource {

    private InscricaoRepository inscricaoRepository;

    private InscricaoService inscricaoService;

    private OficinaService oficinaService;

    private final MailService mailService;

    public AjeResource(InscricaoService inscricaoService,
                       InscricaoRepository inscricaoRepository,
                       OficinaService oficinaService,
                       MailService mailService) {

        this.inscricaoService = inscricaoService;
        this.oficinaService = oficinaService;
        this.inscricaoRepository = inscricaoRepository;
        this.mailService = mailService;

    }

    @GetMapping("/inscricoes")
    public List<Inscricao> listaInscricoes() {
        return inscricaoRepository.findAll();
    }

    @GetMapping("/inscricoes/{id}")
    public InscricaoDTO getInscricao(@PathVariable Long id) {
        return this.inscricaoService.getInscricao(id);
    }

    @PostMapping("/inscricoes")
    public Inscricao salvaInscricao(@RequestBody InscricaoDTO inscricao) throws ParseException {
        Inscricao i = inscricaoService.salvarInscricao(inscricao);
        mailService.sendSuccessMail(i.getPessoa());
        return i;
    }

    @PutMapping("/inscricoes/{id}")
    public Inscricao atualizarInscricao(@RequestBody InscricaoDTO inscricao, @PathVariable Long id) throws ParseException {
        Inscricao i = inscricaoService.atualizarInscricao(inscricao, id);
        return i;
    }

    @GetMapping("/oficinas")
    public List<OficinaDTO> listarOficinas() {
        return oficinaService.listarOficinas();
    }

    @PostMapping("/oficinas")
    public Oficina salvarOficina(@RequestBody OficinaDTO oficina) {
        Oficina o = oficinaService.salvarOficina(oficina);
        return o;
    }

}
