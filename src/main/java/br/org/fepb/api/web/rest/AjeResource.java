package br.org.fepb.api.web.rest;

import br.org.fepb.api.domain.Inscricao;
import br.org.fepb.api.domain.Oficina;
import br.org.fepb.api.domain.pojo.InscricaoPojo;
import br.org.fepb.api.reports.GenericReport;
import br.org.fepb.api.reports.ReportGenerator;
import br.org.fepb.api.repository.InscricaoRepository;
import br.org.fepb.api.security.AuthoritiesConstants;
import br.org.fepb.api.service.InscricaoService;
import br.org.fepb.api.service.MailService;
import br.org.fepb.api.service.OficinaService;
import br.org.fepb.api.service.dto.InscricaoDTO;
import br.org.fepb.api.service.dto.OficinaDTO;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/aje")
public class AjeResource {

    @Autowired
    ServletContext context;

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

    @GetMapping("/report/inscricoes")
    public @ResponseBody void reportInscricoes(HttpServletResponse response) throws Exception {

        List<Inscricao> inscricaoList = inscricaoService.listarInscricoes();
        List<InscricaoPojo> inscricaoPojoList = new ArrayList<InscricaoPojo>();
        for (Inscricao i : inscricaoList) {
            InscricaoPojo iPojo = new InscricaoPojo();
            iPojo.setNome(i.getPessoa().getNome());
            iPojo.setDataNascimento(i.getPessoa().getDataNascimento());
            if (i.getPago().booleanValue() == true) {
                iPojo.setPagamento("Pago");
            } else {
                iPojo.setPagamento("Pendente");
            }
            inscricaoPojoList.add(iPojo);
        }

        JRBeanCollectionDataSource rel = new JRBeanCollectionDataSource(inscricaoPojoList, false);
        GenericReport relatorio = new GenericReport("inscricoes", context);
        ReportGenerator.print(relatorio, new JRBeanCollectionDataSource(inscricaoPojoList   ), response);

    }

}
