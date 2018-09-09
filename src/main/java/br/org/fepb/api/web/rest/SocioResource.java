package br.org.fepb.api.web.rest;

import br.org.fepb.api.domain.HistoricoContribuicao;
import br.org.fepb.api.domain.Socio;
import br.org.fepb.api.domain.pojo.SocioPojo;
import br.org.fepb.api.enumeration.CategoriaContribuicaoEnum;
import br.org.fepb.api.enumeration.ModalidadeAssociacaoEnum;
import br.org.fepb.api.reports.GenericReport;
import br.org.fepb.api.reports.ReportGenerator;
import br.org.fepb.api.service.SocioService;
import br.org.fepb.api.service.dto.HistoricoContribuicaoDTO;
import br.org.fepb.api.service.dto.SocioDTO;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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

    @GetMapping("/reports/associados")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void reportAssociasdos(HttpServletResponse response) throws Exception {
        List<Socio> socios = this.socioService.listarTodosOrdenado();
        List<SocioPojo> socioPojoList = new ArrayList<SocioPojo>();
        for (Socio s : socios) {
            if (s.getPessoa() != null) {
                SocioPojo pojo = new SocioPojo();
                pojo.setNome(s.getPessoa().getNome());

                if (s.getCategoriaAssociacao()!= null && s.getCategoriaAssociacao().equals(CategoriaContribuicaoEnum.CONTRIBUINTE)) {
                    pojo.setModalidade(CategoriaContribuicaoEnum.CONTRIBUINTE.toString());
                } else if (s.getCategoriaAssociacao()!= null && s.getCategoriaAssociacao().equals(CategoriaContribuicaoEnum.PARCEIRO)) {
                    pojo.setModalidade(CategoriaContribuicaoEnum.PARCEIRO.toString());
                } else if (s.getCategoriaAssociacao()!= null && s.getCategoriaAssociacao().equals(CategoriaContribuicaoEnum.DOADOR)) {
                    pojo.setModalidade(CategoriaContribuicaoEnum.DOADOR.toString());
                }

                if (s.getModalidadeAssociacao() != null && s.getModalidadeAssociacao().equals(ModalidadeAssociacaoEnum.CONTRIBUINTE)) {
                    pojo.setModalidade(ModalidadeAssociacaoEnum.CONTRIBUINTE.toString());
                } else if (s.getModalidadeAssociacao() != null && s.getModalidadeAssociacao().equals(ModalidadeAssociacaoEnum.EFETIVO)) {
                    pojo.setModalidade(ModalidadeAssociacaoEnum.EFETIVO.toString());
                } else if (s.getModalidadeAssociacao() != null && s.getModalidadeAssociacao().equals(ModalidadeAssociacaoEnum.FEDERATIVO)) {
                    pojo.setModalidade(ModalidadeAssociacaoEnum.FEDERATIVO.toString());
                }

                socioPojoList.add(pojo);

                JRBeanCollectionDataSource rel = new JRBeanCollectionDataSource(socioPojoList, false);
                GenericReport relatorio = new GenericReport("associados", "fepb-logo.png");
                ReportGenerator.print(relatorio, new JRBeanCollectionDataSource(socioPojoList   ), response);

            }

        }

    }

}
