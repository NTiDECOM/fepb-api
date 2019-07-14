package br.org.fepb.api.domain;

import javax.persistence.*;

@Entity
@Table(name = "configuracoes_evento")
public class ConfiguracaoEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Boolean liberado;

    private String codigo;

    @Column(name = "valor_padrao")
    private Float valorPadrao;

    @Column(name = "valor_desconto")
    private Float valorDesconto;

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
