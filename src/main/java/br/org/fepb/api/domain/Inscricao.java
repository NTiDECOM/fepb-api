package br.org.fepb.api.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "inscricoes")
public class Inscricao implements Serializable {

    private static final long serialVersionUID = -8308858717357351308L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trabalhador")
    private Boolean trabalhador;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "instituicao")
    private String instituicao;

    @Column(name = "nome_coordenador")
    private String nomeCoordenador;

    @Column(name = "email_coordenador")
    private String emailCoordenador;

    @Column(name = "valida")
    private Boolean valida;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "id_oficina")
    private Oficina oficina;

    @Column(name = "pago")
    private Boolean pago;

    @Column(name = "flag_email_autoriza")
    private Boolean flagEmailAutorizacao;

    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private Cidade cidade;

    public Boolean getTrabalhador() {
        return trabalhador;
    }

    public void setTrabalhador(Boolean trabalhador) {
        this.trabalhador = trabalhador;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getNomeCoordenador() {
        return nomeCoordenador;
    }

    public void setNomeCoordenador(String nomeCoordenador) {
        this.nomeCoordenador = nomeCoordenador;
    }

    public String getEmailCoordenador() {
        return emailCoordenador;
    }

    public void setEmailCoordenador(String emailCoordenador) {
        this.emailCoordenador = emailCoordenador;
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

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public Boolean getValida() {
        return valida;
    }

    public void setValida(Boolean valida) {
        this.valida = valida;
    }

    public Boolean getFlagEmailAutorizacao() {
        return flagEmailAutorizacao;
    }

    public void setFlagEmailAutorizacao(Boolean flagEmailAutorizacao) {
        this.flagEmailAutorizacao = flagEmailAutorizacao;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
