package br.org.fepb.api.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "oficinas")
public class Oficina implements Serializable {

    private static final long serialVersionUID = -3060483345622568569L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Integer vagas;

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
}
