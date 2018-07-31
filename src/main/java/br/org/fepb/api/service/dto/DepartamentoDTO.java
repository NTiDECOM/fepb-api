package br.org.fepb.api.service.dto;

import br.org.fepb.api.domain.Departamento;

public class DepartamentoDTO {

    private Long id;

    private String nome;

    private String responsavel;

    private String email;

    public DepartamentoDTO() {}

    public DepartamentoDTO(Departamento d) {
        this.id = d.getId();
        this.nome = d.getNome();
        this.responsavel = d.getResponsavel();
        this.email = d.getEmail();
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

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
