package br.org.fepb.api.service.dto;

import br.org.fepb.api.domain.Local;

public class LocalDTO {

    private Long id;
    private String nome;

    public LocalDTO() {}

    public LocalDTO(Local l) {
        this.id = l.getId();
        this.nome = l.getNome();
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
