package br.org.fepb.api.domain;

import br.org.fepb.api.enumeration.MetodoContribuicaoEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "socios")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Socio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_adesao")
    @Temporal(TemporalType.DATE)
    private Date dataAdesao;

    @Column(name = "valor_contribuicao")
    private Double valorContribuicao;

    @Column(name = "vencimento_contribuicao")
    private String vencimentoContribuicao;

    @Column(name = "metodo_contribuicao")
    @Enumerated
    private MetodoContribuicaoEnum metodoContribuicao;


    @JoinColumn(name = "id_pessoa")
    @OneToOne(cascade = CascadeType.ALL)
    private Pessoa pessoa;

    public Date getDataAdesao() {
        return dataAdesao;
    }

    public void setDataAdesao(Date dataAdesao) {
        this.dataAdesao = dataAdesao;
    }

    public Double getValorContribuicao() {
        return valorContribuicao;
    }

    public void setValorContribuicao(Double valorContribuicao) {
        this.valorContribuicao = valorContribuicao;
    }

    public String getVencimentoContribuicao() {
        return vencimentoContribuicao;
    }

    public void setVencimentoContribuicao(String vencimentoContribuicao) {
        this.vencimentoContribuicao = vencimentoContribuicao;
    }

    public MetodoContribuicaoEnum getMetodoContribuicao() {
        return metodoContribuicao;
    }

    public void setMetodoContribuicao(MetodoContribuicaoEnum metodoContribuicao) {
        this.metodoContribuicao = metodoContribuicao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
