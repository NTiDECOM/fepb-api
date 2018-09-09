package br.org.fepb.api.web.rest;

import br.org.fepb.api.domain.Inscricao;
import br.org.fepb.api.domain.Oficina;
import br.org.fepb.api.domain.pojo.InscricaoPojo;
import br.org.fepb.api.reports.GenericReport;
import br.org.fepb.api.reports.ReportGenerator;
import br.org.fepb.api.repository.InscricaoRepository;
import br.org.fepb.api.security.AuthoritiesConstants;
import br.org.fepb.api.service.FileStorageService;
import br.org.fepb.api.service.InscricaoService;
import br.org.fepb.api.service.MailService;
import br.org.fepb.api.service.OficinaService;
import br.org.fepb.api.service.dto.InscricaoDTO;
import br.org.fepb.api.service.dto.OficinaDTO;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/aje")
public class AjeResource {

    private static final Logger logger = LoggerFactory.getLogger(AjeResource.class);

    private ServletContext context;

    private FileStorageService fileStorageService;

    private InscricaoRepository inscricaoRepository;

    private InscricaoService inscricaoService;

    private OficinaService oficinaService;

    private final MailService mailService;

    public AjeResource(InscricaoService inscricaoService,
                       InscricaoRepository inscricaoRepository,
                       OficinaService oficinaService,
                       MailService mailService,
                       ServletContext context) {

        this.inscricaoService = inscricaoService;
        this.oficinaService = oficinaService;
        this.inscricaoRepository = inscricaoRepository;
        this.mailService = mailService;
        this.context = context;
        try {
            this.fileStorageService = new FileStorageService();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/excel")
    public ResponseEntity<Resource> gerarExcel(HttpServletRequest request) throws Exception {
        inscricaoService.gerarExcel();

        Resource resource = fileStorageService.loadFileAsResource("INSCRICOES_AJE.xlsx");


        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }


        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);

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

    @PostMapping("/inscricoes/email-autorizacao/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void enviarEmailAutorizacaoPorId(@PathVariable Long id) throws ParseException {
        this.inscricaoService.enviarEmailAutorizacao(mailService, id);
    }

    @PostMapping("/inscricoes/email-autorizacao")
    @ResponseStatus(HttpStatus.OK)
    public void enviarEmailAutorizacao() throws ParseException {
        this.inscricaoService.enviarEmailsAutorizacao(mailService);
    }

    @GetMapping("/report/inscricoes")
    public @ResponseBody void reportInscricoes(HttpServletResponse response, HttpServletRequest request) throws Exception {

        List<Inscricao> inscricaoList = inscricaoService.listarInscricoes();
        List<InscricaoPojo> inscricaoPojoList = new ArrayList<InscricaoPojo>();
        for (Inscricao i : inscricaoList) {
            InscricaoPojo iPojo = new InscricaoPojo();
            iPojo.setNome(WordUtils.capitalizeFully(i.getPessoa().getNome()));
            iPojo.setDataNascimento(i.getPessoa().getDataNascimento());
            if (i.getPago().booleanValue() == true) {
                iPojo.setPagamento("Pago");
            } else {
                iPojo.setPagamento("Pendente");
            }
            inscricaoPojoList.add(iPojo);
        }

        JRBeanCollectionDataSource rel = new JRBeanCollectionDataSource(inscricaoPojoList, false);
        GenericReport relatorio = new GenericReport("inscricoes", "aje-logo.png");
        ReportGenerator.print(relatorio, new JRBeanCollectionDataSource(inscricaoPojoList   ), response);

    }

    @GetMapping("/report/oficinas/{id}")
    public @ResponseBody void reportOficinas(HttpServletResponse response, @PathVariable Long id) throws Exception {

        Oficina o = this.oficinaService.buscarOficina(id);
        List<Inscricao> inscricaoList = inscricaoService.listarPorOficina(o);
        List<InscricaoPojo> inscricaoPojoList = new ArrayList<InscricaoPojo>();
        for (Inscricao i : inscricaoList) {
            InscricaoPojo iPojo = new InscricaoPojo();
            iPojo.setNome(WordUtils.capitalizeFully(i.getPessoa().getNome()));
            iPojo.setDataNascimento(i.getPessoa().getDataNascimento());
            if (i.getPago().booleanValue() == true) {
                iPojo.setPagamento("Pago");
            } else {
                iPojo.setPagamento("Pendente");
            }
            inscricaoPojoList.add(iPojo);
        }

        JRBeanCollectionDataSource rel = new JRBeanCollectionDataSource(inscricaoPojoList, false);
        GenericReport relatorio = new GenericReport("oficina", "aje-logo.png");
        relatorio.adicionarParametro("OFICINA", o.getNome());
        ReportGenerator.print(relatorio, new JRBeanCollectionDataSource(inscricaoPojoList   ), response);

    }

}
