package br.org.fepb.api.domain;

import br.org.fepb.api.enumeration.RestricaoAlimentarEnum;
import br.org.fepb.api.enumeration.SexoEnum;
import br.org.fepb.api.enumeration.TipoPessoaEnum;
import br.org.fepb.api.enumeration.TipoSanguineoEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pessoas")
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "como_chamar")
    private String comoChamar;

    @Enumerated
    @Column(name = "tipo_pessoa")
    private TipoPessoaEnum tipoPessoa;

    @Enumerated
    @Column(name = "sexo")
    private SexoEnum sexo;

    @Enumerated
    @Column(name = "tipo_sanguineo")
    private TipoSanguineoEnum tipoSanguineo;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "email")
    private String email;

    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Column(name = "restricao_saude")
    private String restricaoSaude;

    @Column(name = "restricao_alimentar")
    private RestricaoAlimentarEnum restricaoAlimentar;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getComoChamar() {
        return comoChamar;
    }

    public void setComoChamar(String comoChamar) {
        this.comoChamar = comoChamar;
    }

    public SexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }

    public TipoSanguineoEnum getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(TipoSanguineoEnum tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getRestricaoSaude() {
        return restricaoSaude;
    }

    public void setRestricaoSaude(String restricaoSaude) {
        this.restricaoSaude = restricaoSaude;
    }

    public RestricaoAlimentarEnum getRestricaoAlimentar() {
        return restricaoAlimentar;
    }

    public void setRestricaoAlimentar(RestricaoAlimentarEnum restricaoAlimentar) {
        this.restricaoAlimentar = restricaoAlimentar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPessoaEnum getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoaEnum tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
