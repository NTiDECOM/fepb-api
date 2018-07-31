package br.org.fepb.api.service.dto;

import br.org.fepb.api.domain.Socio;
import br.org.fepb.api.enumeration.CategoriaContribuicaoEnum;
import br.org.fepb.api.enumeration.MetodoContribuicaoEnum;
import br.org.fepb.api.enumeration.ModalidadeAssociacaoEnum;

public class SocioDTO {

    private Long id;

    private String dataAdesao;

    private Double valorContribuicao;

    private String vencimentoContribuicao;

    private String metodoContribuicao;

    private String telefone;

    private String modalidadeAssociacao;

    private String categoriaAssociacao;

    private PessoaDTO pessoa;

    public SocioDTO() {}

    public SocioDTO(Socio s) {

        this.id = s.getId();
        this.dataAdesao = s.getDataAdesao().toString();
        this.valorContribuicao = s.getValorContribuicao();
        this.vencimentoContribuicao = s.getVencimentoContribuicao();
        this.telefone = s.getTelefone();

        if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.DINHEIRO)) {
            this.metodoContribuicao = MetodoContribuicaoEnum.DINHEIRO.toString();
        } else if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.DEPOSITO)) {
            this.metodoContribuicao = MetodoContribuicaoEnum.DEPOSITO.toString();
        } else if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.CARTAO_DEBITO)) {
            this.metodoContribuicao = MetodoContribuicaoEnum.CARTAO_DEBITO.toString();
        } else if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.CARTAO_CREDITO)) {
            this.metodoContribuicao = MetodoContribuicaoEnum.CARTAO_CREDITO.toString();
        } else if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.TRANSFERENCIA)) {
            this.metodoContribuicao = MetodoContribuicaoEnum.TRANSFERENCIA.toString();
        }

        if (s.getModalidadeAssociacao().equals(ModalidadeAssociacaoEnum.EFETIVO)) {
            this.modalidadeAssociacao = ModalidadeAssociacaoEnum.EFETIVO.toString();
        } else if (s.getModalidadeAssociacao().equals(ModalidadeAssociacaoEnum.FEDERATIVO)) {
            this.modalidadeAssociacao = ModalidadeAssociacaoEnum.FEDERATIVO.toString();
        } else if (s.getModalidadeAssociacao().equals(ModalidadeAssociacaoEnum.CONTRIBUINTE)) {
            this.modalidadeAssociacao = ModalidadeAssociacaoEnum.CONTRIBUINTE.toString();
        }

        if (s.getCategoriaAssociacao().equals(CategoriaContribuicaoEnum.DOADOR)) {
            this.categoriaAssociacao = CategoriaContribuicaoEnum.DOADOR.toString();
        } else if (s.getCategoriaAssociacao().equals(CategoriaContribuicaoEnum.PARCEIRO)) {
            this.categoriaAssociacao = CategoriaContribuicaoEnum.PARCEIRO.toString();
        } else if (s.getCategoriaAssociacao().equals(CategoriaContribuicaoEnum.CONTRIBUINTE)) {
            this.categoriaAssociacao = CategoriaContribuicaoEnum.CONTRIBUINTE.toString();
        }

        this.pessoa = new PessoaDTO(s.getPessoa());

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataAdesao() {
        return dataAdesao;
    }

    public void setDataAdesao(String dataAdesao) {
        this.dataAdesao = dataAdesao;
    }

    public Double getValorContribuicao() {
        return valorContribuicao;
    }

    public void setValorContribuicao(Double valorContribuicao) {
        this.valorContribuicao = valorContribuicao;
    }

    public String getVencimentoContribuicao() {
        return vencimentoContribuicao;
    }

    public void setVencimentoContribuicao(String vencimentoContribuicao) {
        this.vencimentoContribuicao = vencimentoContribuicao;
    }

    public String getMetodoContribuicao() {
        return metodoContribuicao;
    }

    public void setMetodoContribuicao(String metodoContribuicao) {
        this.metodoContribuicao = metodoContribuicao;
    }

    public PessoaDTO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaDTO pessoa) {
        this.pessoa = pessoa;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getModalidadeAssociacao() {
        return modalidadeAssociacao;
    }

    public void setModalidadeAssociacao(String modalidadeAssociacao) {
        this.modalidadeAssociacao = modalidadeAssociacao;
    }

    public String getCategoriaAssociacao() {
        return categoriaAssociacao;
    }

    public void setCategoriaAssociacao(String categoriaAssociacao) {
        this.categoriaAssociacao = categoriaAssociacao;
    }
}
