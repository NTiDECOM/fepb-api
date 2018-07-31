package br.org.fepb.api.service;

import br.org.fepb.api.domain.HistoricoContribuicao;
import br.org.fepb.api.domain.Pessoa;
import br.org.fepb.api.domain.Socio;
import br.org.fepb.api.enumeration.*;
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

        if (s.getPessoa().getTipoSanguineo() != null && s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.A_POSITIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.A_POSITIVO);
        } else if (s.getPessoa().getTipoSanguineo() != null && s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.A_NEGATIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.A_NEGATIVO);
        } else if (s.getPessoa().getTipoSanguineo() != null && s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.B_POSITIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.B_POSITIVO);
        } else if (s.getPessoa().getTipoSanguineo() != null && s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.B_NEGATIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.B_NEGATIVO);
        } else if (s.getPessoa().getTipoSanguineo() != null && s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.AB_POSITIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.AB_POSITIVO);
        } else if (s.getPessoa().getTipoSanguineo() != null && s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.AB_NEGATIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.AB_NEGATIVO);
        } else if (s.getPessoa().getTipoSanguineo() != null && s.getPessoa().getTipoSanguineo()
            .equals(TipoSanguineoEnum.O_POSITIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.O_POSITIVO);
        } else if (s.getPessoa().getTipoSanguineo() != null && s.getPessoa().getTipoSanguineo()
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
        } else if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.CARTAO_DEBITO.toString())) {
            newSocio.setMetodoContribuicao(MetodoContribuicaoEnum.CARTAO_DEBITO);
        } else if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.CARTAO_CREDITO.toString())) {
            newSocio.setMetodoContribuicao(MetodoContribuicaoEnum.CARTAO_CREDITO);
        } else if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.TRANSFERENCIA.toString())) {
            newSocio.setMetodoContribuicao(MetodoContribuicaoEnum.TRANSFERENCIA);
        }

        if (s.getModalidadeAssociacao().equals(ModalidadeAssociacaoEnum.EFETIVO.toString())) {
            newSocio.setModalidadeAssociacao(ModalidadeAssociacaoEnum.EFETIVO);
        } else if (s.getModalidadeAssociacao().equals(ModalidadeAssociacaoEnum.FEDERATIVO.toString())) {
            newSocio.setModalidadeAssociacao(ModalidadeAssociacaoEnum.FEDERATIVO);
        } else if (s.getModalidadeAssociacao().equals(ModalidadeAssociacaoEnum.CONTRIBUINTE.toString())) {
            newSocio.setModalidadeAssociacao(ModalidadeAssociacaoEnum.CONTRIBUINTE);
        }

        if (s.getCategoriaAssociacao().equals(CategoriaContribuicaoEnum.DOADOR.toString())) {
            newSocio.setCategoriaAssociacao(CategoriaContribuicaoEnum.DOADOR);
        } else if (s.getCategoriaAssociacao().equals(CategoriaContribuicaoEnum.PARCEIRO.toString())) {
            newSocio.setCategoriaAssociacao(CategoriaContribuicaoEnum.PARCEIRO);
        } else if (s.getCategoriaAssociacao().equals(CategoriaContribuicaoEnum.CONTRIBUINTE.toString())) {
            newSocio.setCategoriaAssociacao(CategoriaContribuicaoEnum.CONTRIBUINTE);
        }

        newSocio.setValorContribuicao(s.getValorContribuicao());
        newSocio.setVencimentoContribuicao(s.getVencimentoContribuicao());
        newSocio.setTelefone(s.getTelefone());
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
        } else if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.CARTAO_DEBITO.toString())) {
            sUpdate.setMetodoContribuicao(MetodoContribuicaoEnum.CARTAO_DEBITO);
        } else if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.CARTAO_CREDITO.toString())) {
            sUpdate.setMetodoContribuicao(MetodoContribuicaoEnum.CARTAO_CREDITO);
        }
        else if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.TRANSFERENCIA.toString())) {
            sUpdate.setMetodoContribuicao(MetodoContribuicaoEnum.TRANSFERENCIA);
        }

        if (s.getModalidadeAssociacao().equals(ModalidadeAssociacaoEnum.EFETIVO.toString())) {
            sUpdate.setModalidadeAssociacao(ModalidadeAssociacaoEnum.EFETIVO);
        } else if (s.getModalidadeAssociacao().equals(ModalidadeAssociacaoEnum.FEDERATIVO.toString())) {
            sUpdate.setModalidadeAssociacao(ModalidadeAssociacaoEnum.FEDERATIVO);
        } else if (s.getModalidadeAssociacao().equals(ModalidadeAssociacaoEnum.CONTRIBUINTE.toString())) {
            sUpdate.setModalidadeAssociacao(ModalidadeAssociacaoEnum.CONTRIBUINTE);
        }

        if (s.getCategoriaAssociacao().equals(CategoriaContribuicaoEnum.DOADOR.toString())) {
            sUpdate.setCategoriaAssociacao(CategoriaContribuicaoEnum.DOADOR);
        } else if (s.getCategoriaAssociacao().equals(CategoriaContribuicaoEnum.PARCEIRO.toString())) {
            sUpdate.setCategoriaAssociacao(CategoriaContribuicaoEnum.PARCEIRO);
        } else if (s.getCategoriaAssociacao().equals(CategoriaContribuicaoEnum.CONTRIBUINTE.toString())) {
            sUpdate.setCategoriaAssociacao(CategoriaContribuicaoEnum.CONTRIBUINTE);
        }

        sUpdate.setValorContribuicao(s.getValorContribuicao());
        sUpdate.setVencimentoContribuicao(s.getVencimentoContribuicao());
        sUpdate.setTelefone(s.getTelefone());

        return this.socioRepository.save(sUpdate);
    }

    public List<Socio> listarSocios() {
        return this.socioRepository.findAll();
    }

    public Socio buscarPorId(Long id) {
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
