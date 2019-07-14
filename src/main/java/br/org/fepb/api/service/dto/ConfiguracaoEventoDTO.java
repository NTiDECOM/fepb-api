package br.org.fepb.api.service.dto;

import br.org.fepb.api.domain.ConfiguracaoEvento;

public class ConfiguracaoEventoDTO {

    private Long id;
    private String nome;
    private Boolean liberado;
    private String codigo;
    private Float valorPadrao;
    private Float valorDesconto;

    public ConfiguracaoEventoDTO() {}

    public ConfiguracaoEventoDTO(ConfiguracaoEvento c) {
        this.id = c.getId();
        this.nome = c.getNome();
        this.liberado = c.getLiberado();
        this.codigo = c.getCodigo();
        this.valorPadrao = c.getValorPadrao();
        this.valorDesconto = c.getValorDesconto();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getLiberado() {
        return liberado;
    }

    public void setLiberado(Boolean liberado) {
        this.liberado = liberado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Float getValorPadrao() {
        return valorPadrao;
    }

    public void setValorPadrao(Float valorPadrao) {
        this.valorPadrao = valorPadrao;
    }

    public Float getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Float valorDesconto) {
        this.valorDesconto = valorDesconto;
    }
}
