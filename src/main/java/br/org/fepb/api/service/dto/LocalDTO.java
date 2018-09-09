package br.org.fepb.api.service.dto;

import br.org.fepb.api.domain.Local;
import br.org.fepb.api.enumeration.TipoLocalEnum;

public class LocalDTO {

    private Long id;
    private String nome;
    private String tipoLocal;

    public LocalDTO() {}

    public LocalDTO(Local l) {
        this.id = l.getId();
        this.nome = l.getNome();

        if (l.getTipoLocal().equals(TipoLocalEnum.INTERNO)) {
            this.tipoLocal = TipoLocalEnum.INTERNO.toString();
        } else if (l.getTipoLocal().equals(TipoLocalEnum.EXTERNO)) {
            this.tipoLocal = TipoLocalEnum.EXTERNO.toString();
        }
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

    public String getTipoLocal() {
        return tipoLocal;
    }

    public void setTipoLocal(String tipoLocal) {
        this.tipoLocal = tipoLocal;
    }
}
