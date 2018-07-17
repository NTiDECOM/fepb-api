package br.org.fepb.api.web.rest;

import br.org.fepb.api.domain.Inscricao;
import br.org.fepb.api.repository.InscricaoRepository;
import br.org.fepb.api.security.AuthoritiesConstants;
import br.org.fepb.api.service.InscricaoService;
import br.org.fepb.api.service.MailService;
import br.org.fepb.api.service.dto.InscricaoDTO;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/aje")
public class AjeResource {

    private InscricaoRepository inscricaoRepository;

    private InscricaoService inscricaoService;

    private final MailService mailService;

    public AjeResource(InscricaoService inscricaoService, InscricaoRepository inscricaoRepository, MailService mailService) {

        this.inscricaoService = inscricaoService;
        this.inscricaoRepository = inscricaoRepository;
        this.mailService = mailService;

    }

    @GetMapping("/inscricoes")
    public List<Inscricao> listaInscricoes() {

        return inscricaoRepository.findAll();

    }

    @PostMapping("/inscricoes")
    public Inscricao salvaInscricao(@RequestBody InscricaoDTO inscricao) throws ParseException {
        Inscricao i = inscricaoService.salvarInscricao(inscricao);
        mailService.sendSuccessMail(i.getPessoa());
        return i;
    }

}
