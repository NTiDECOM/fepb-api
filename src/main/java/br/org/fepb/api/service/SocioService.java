package br.org.fepb.api.service;

import br.org.fepb.api.domain.HistoricoContribuicao;
import br.org.fepb.api.domain.Pessoa;
import br.org.fepb.api.domain.Socio;
import br.org.fepb.api.enumeration.MetodoContribuicaoEnum;
import br.org.fepb.api.enumeration.SexoEnum;
import br.org.fepb.api.enumeration.TipoSanguineoEnum;
import br.org.fepb.api.repository.HistoricoContribuicaoRepository;
import br.org.fepb.api.repository.SocioRepository;
import br.org.fepb.api.service.dto.HistoricoContribuicaoDTO;
import br.org.fepb.api.service.dto.SocioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class SocioService {

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatterUpdate = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private SocioRepository socioRepository;

    @Autowired
    private HistoricoContribuicaoRepository historicoContribuicaoRepository;

    public Socio salvarSocio(SocioDTO s) throws ParseException {

        Pessoa newPessoa = new Pessoa();
        newPessoa.setNome(s.getPessoa().getNome());
        newPessoa.setComoChamar(s.getPessoa().getComoChamar());
        newPessoa.setEmail(s.getPessoa().getEmail());
        newPessoa.setRestricaoSaude(s.getPessoa().getRestricaoSaude());
        newPessoa.setCpf(s.getPessoa().getCpf());

        if (s.getPessoa().getSexo().equals(SexoEnum.MASCULINO.toString())) {
            newPessoa.setSexo(SexoEnum.MASCULINO);
        } else if (s.getPessoa().getSexo().equals(SexoEnum.FEMININO.toString())) {
            newPessoa.setSexo(SexoEnum.FEMININO);
        }

        if (s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.A_POSITIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.A_POSITIVO);
        } else if (s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.A_NEGATIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.A_NEGATIVO);
        } else if (s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.B_POSITIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.B_POSITIVO);
        } else if (s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.B_NEGATIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.B_NEGATIVO);
        } else if (s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.AB_POSITIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.AB_POSITIVO);
        } else if (s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.AB_NEGATIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.AB_NEGATIVO);
        } else if (s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.O_POSITIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.O_POSITIVO);
        } else if (s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.O_NEGATIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.O_NEGATIVO);
        }

        newPessoa.setEmail(s.getPessoa().getEmail());
        newPessoa.setDataNascimento(formatter.parse(s.getPessoa().getDataNascimento()));

        Socio newSocio = new Socio();
        newSocio.setDataAdesao(formatter.parse(s.getDataAdesao()));

        if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.DINHEIRO.toString())) {
            newSocio.setMetodoContribuicao(MetodoContribuicaoEnum.DINHEIRO);
        } else if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.DEPOSITO.toString())) {
            newSocio.setMetodoContribuicao(MetodoContribuicaoEnum.DEPOSITO);
        } else if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.CHEQUE.toString())) {
            newSocio.setMetodoContribuicao(MetodoContribuicaoEnum.CHEQUE);
        } else if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.CARTAO.toString())) {
            newSocio.setMetodoContribuicao(MetodoContribuicaoEnum.CARTAO);
        }

        newSocio.setValorContribuicao(s.getValorContribuicao());
        newSocio.setVencimentoContribuicao(s.getVencimentoContribuicao());
        newSocio.setPessoa(newPessoa);

        return this.socioRepository.save(newSocio);

    }

    public Socio atualizarSocio(SocioDTO s) throws ParseException {

        Socio sUpdate = this.socioRepository.getOne(s.getId());
        sUpdate.getPessoa().setNome(s.getPessoa().getNome());
        sUpdate.getPessoa().setComoChamar(s.getPessoa().getComoChamar());
        sUpdate.getPessoa().setEmail(s.getPessoa().getEmail());
        sUpdate.getPessoa().setRestricaoSaude(s.getPessoa().getRestricaoSaude());
        sUpdate.getPessoa().setCpf(s.getPessoa().getCpf());

        if (s.getPessoa().getSexo().equals(SexoEnum.MASCULINO.toString())) {
            sUpdate.getPessoa().setSexo(SexoEnum.MASCULINO);
        } else if (s.getPessoa().getSexo().equals(SexoEnum.FEMININO.toString())) {
            sUpdate.getPessoa().setSexo(SexoEnum.FEMININO);
        }

        if (s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.A_POSITIVO.toString())) {
            sUpdate.getPessoa().setTipoSanguineo(TipoSanguineoEnum.A_POSITIVO);
        } else if (s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.A_NEGATIVO.toString())) {
            sUpdate.getPessoa().setTipoSanguineo(TipoSanguineoEnum.A_NEGATIVO);
        } else if (s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.B_POSITIVO.toString())) {
            sUpdate.getPessoa().setTipoSanguineo(TipoSanguineoEnum.B_POSITIVO);
        } else if (s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.B_NEGATIVO.toString())) {
            sUpdate.getPessoa().setTipoSanguineo(TipoSanguineoEnum.B_NEGATIVO);
        } else if (s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.AB_POSITIVO.toString())) {
            sUpdate.getPessoa().setTipoSanguineo(TipoSanguineoEnum.AB_POSITIVO);
        } else if (s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.AB_NEGATIVO.toString())) {
            sUpdate.getPessoa().setTipoSanguineo(TipoSanguineoEnum.AB_NEGATIVO);
        } else if (s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.O_POSITIVO.toString())) {
            sUpdate.getPessoa().setTipoSanguineo(TipoSanguineoEnum.O_POSITIVO);
        } else if (s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.O_NEGATIVO.toString())) {
            sUpdate.getPessoa().setTipoSanguineo(TipoSanguineoEnum.O_NEGATIVO);
        }

        sUpdate.getPessoa().setEmail(s.getPessoa().getEmail());
        sUpdate.getPessoa().setDataNascimento(formatter.parse(s.getPessoa().getDataNascimento()));

        sUpdate.setDataAdesao(formatterUpdate.parse(s.getDataAdesao()));

        if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.DINHEIRO.toString())) {
            sUpdate.setMetodoContribuicao(MetodoContribuicaoEnum.DINHEIRO);
        } else if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.DEPOSITO.toString())) {
            sUpdate.setMetodoContribuicao(MetodoContribuicaoEnum.DEPOSITO);
        } else if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.CHEQUE.toString())) {
            sUpdate.setMetodoContribuicao(MetodoContribuicaoEnum.CHEQUE);
        } else if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.CARTAO.toString())) {
            sUpdate.setMetodoContribuicao(MetodoContribuicaoEnum.CARTAO);
        }

        sUpdate.setValorContribuicao(s.getValorContribuicao());
        sUpdate.setVencimentoContribuicao(s.getVencimentoContribuicao());

        return this.socioRepository.save(sUpdate);
    }

    public List<Socio> listarSocios() {
        return this.socioRepository.findAll();
    }

    public Socio bucasPorId(Long id) {
        Optional<Socio> s = this.socioRepository.findById(id);
        return s.get();
    }

    public List<HistoricoContribuicao> listarPorSocio(Socio s) {
        return this.historicoContribuicaoRepository.findBySocio(s);
    }

    public HistoricoContribuicao salvarContribuicao(HistoricoContribuicaoDTO h) throws ParseException {

        Socio s = this.socioRepository.getOne(h.getSocio().getId());

        HistoricoContribuicao newH = new HistoricoContribuicao();
        newH.setDataPagamento(formatter.parse(h.getDataPagamento()));
        newH.setValorPago(h.getValorPago());
        newH.setSocio(s);

        return this.historicoContribuicaoRepository.save(newH);

    }

    public List<HistoricoContribuicao> buscarContribuicaoPorSocio(Socio s) {
        return this.historicoContribuicaoRepository.findBySocio(s);
    }

}
