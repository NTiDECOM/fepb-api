package br.org.fepb.api.service.dto;

import br.org.fepb.api.domain.Oficina;

public class OficinaDTO {

    private Long id;

    private String nome;

    private Integer vagas;

    private Integer numInscricoes;

    public OficinaDTO() {}

    public OficinaDTO(Oficina o) {
        this.id = o.getId();
        this.nome = o.getNome();
        this.vagas = o.getVagas();
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

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public Integer getNumInscricoes() {
        return numInscricoes;
    }

    public void setNumInscricoes(Integer numInscricoes) {
        this.numInscricoes = numInscricoes;
    }
}
