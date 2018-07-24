package br.org.fepb.api.service.dto;

import br.org.fepb.api.domain.HistoricoContribuicao;

public class HistoricoContribuicaoDTO {

    private Long id;

    private SocioDTO socio;

    private Double valorPago;

    private String dataPagamento;

    public HistoricoContribuicaoDTO() { }

    public HistoricoContribuicaoDTO(HistoricoContribuicao h) {

        this.id = h.getId();
        this.socio = new SocioDTO(h.getSocio());
        this.valorPago = h.getValorPago();
        this.dataPagamento = h.getDataPagamento().toString();

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
}
