package br.org.fepb.api.service.dto;

import br.org.fepb.api.domain.HistoricoContribuicao;
import br.org.fepb.api.enumeration.MetodoContribuicaoEnum;

import java.text.SimpleDateFormat;

public class HistoricoContribuicaoDTO {

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    private Long id;

    private SocioDTO socio;

    private Double valorPago;

    private String dataPagamento;

    private String mesAnoReferencia;

    private String metodoContribuicao;

    public HistoricoContribuicaoDTO() { }

    public HistoricoContribuicaoDTO(HistoricoContribuicao h) {

        this.id = h.getId();
        this.socio = new SocioDTO(h.getSocio());
        this.valorPago = h.getValorPago();
        this.dataPagamento = formatter.format(h.getDataPagamento());
        this.mesAnoReferencia = h.getMesAnoReferencia();

        if (h.getMetodoContribuicao() != null && h.getMetodoContribuicao().equals(MetodoContribuicaoEnum.DINHEIRO)) {
            this.metodoContribuicao = MetodoContribuicaoEnum.DINHEIRO.toString();
        } else if (h.getMetodoContribuicao() != null && h.getMetodoContribuicao().equals(MetodoContribuicaoEnum.DEPOSITO)) {
            this.metodoContribuicao = MetodoContribuicaoEnum.DEPOSITO.toString();
        } else if (h.getMetodoContribuicao() != null && h.getMetodoContribuicao().equals(MetodoContribuicaoEnum.CARTAO_CREDITO)) {
            this.metodoContribuicao = MetodoContribuicaoEnum.CARTAO_CREDITO.toString();
        } else if (h.getMetodoContribuicao() != null && h.getMetodoContribuicao().equals(MetodoContribuicaoEnum.CARTAO_DEBITO)) {
            this.metodoContribuicao = MetodoContribuicaoEnum.CARTAO_DEBITO.toString();
        } else if (h.getMetodoContribuicao() != null && h.getMetodoContribuicao().equals(MetodoContribuicaoEnum.TRANSFERENCIA)) {
            this.metodoContribuicao = MetodoContribuicaoEnum.TRANSFERENCIA.toString();
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SocioDTO getSocio() {
        return socio;
    }

    public void setSocio(SocioDTO socio) {
        this.socio = socio;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getMesAnoReferencia() {
        return mesAnoReferencia;
    }

    public void setMesAnoReferencia(String mesAnoReferencia) {
        this.mesAnoReferencia = mesAnoReferencia;
    }

    public String getMetodoContribuicao() {
        return metodoContribuicao;
    }

    public void setMetodoContribuicao(String metodoContribuicao) {
        this.metodoContribuicao = metodoContribuicao;
    }
}
