package br.org.fepb.api.domain;

import br.org.fepb.api.enumeration.TipoLocalEnum;

import javax.persistence.*;

@Entity
@Table(name = "locais")
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated
    private TipoLocalEnum tipoLocal;

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

    public TipoLocalEnum getTipoLocal() {
        return tipoLocal;
    }

    public void setTipoLocal(TipoLocalEnum tipoLocal) {
        this.tipoLocal = tipoLocal;
    }
}
