package br.org.fepb.api.service.dto;

import br.org.fepb.api.domain.Cidade;

public class CidadeDTO {

    private Long id;

    private String nome;

    private CidadeDTO() { }

    public CidadeDTO(Cidade c) {
        this.id = c.getId();
        this.nome = c.getNome();
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
}
