package br.org.fepb.api.domain;

import br.org.fepb.api.enumeration.MetodoContribuicaoEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "historico_contribuicao")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HistoricoContribuicao implements Serializable {

    private static final long serialVersionUID = 8210077396825760309L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_socio")
    private Socio socio;

    @Column(name = "valor_pago")
    private Double valorPago;

    @Column(name = "data_pagamento")
    private Date dataPagamento;

    @Column(name = "metodo_contribuicao")
    private MetodoContribuicaoEnum metodoContribuicao;

    @Column(name = "mes_ano_referencia")
    private String mesAnoReferencia;

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MetodoContribuicaoEnum getMetodoContribuicao() {
        return metodoContribuicao;
    }

    public void setMetodoContribuicao(MetodoContribuicaoEnum metodoContribuicao) {
        this.metodoContribuicao = metodoContribuicao;
    }

    public String getMesAnoReferencia() {
        return mesAnoReferencia;
    }

    public void setMesAnoReferencia(String mesAnoReferencia) {
        this.mesAnoReferencia = mesAnoReferencia;
    }
}
