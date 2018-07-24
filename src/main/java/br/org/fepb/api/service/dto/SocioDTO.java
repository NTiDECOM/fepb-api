package br.org.fepb.api.service.dto;

import br.org.fepb.api.domain.Socio;
import br.org.fepb.api.enumeration.MetodoContribuicaoEnum;

public class SocioDTO {

    private Long id;

    private String dataAdesao;

    private Double valorContribuicao;

    private String vencimentoContribuicao;

    private String metodoContribuicao;

    private PessoaDTO pessoa;

    public SocioDTO() {}

    public SocioDTO(Socio s) {

        this.id = s.getId();
        this.dataAdesao = s.getDataAdesao().toString();
        this.valorContribuicao = s.getValorContribuicao();
        this.vencimentoContribuicao = s.getVencimentoContribuicao();

        if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.DINHEIRO)) {
            this.metodoContribuicao = MetodoContribuicaoEnum.DINHEIRO.toString();
        } else if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.DEPOSITO)) {
            this.metodoContribuicao = MetodoContribuicaoEnum.DEPOSITO.toString();
        } else if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.CHEQUE)) {
            this.metodoContribuicao = MetodoContribuicaoEnum.CHEQUE.toString();
        } else if (s.getMetodoContribuicao().equals(MetodoContribuicaoEnum.CARTAO)) {
            this.metodoContribuicao = MetodoContribuicaoEnum.CARTAO.toString();
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
}
