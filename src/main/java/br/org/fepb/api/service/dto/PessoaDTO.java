package br.org.fepb.api.service.dto;

import br.org.fepb.api.domain.Pessoa;
import br.org.fepb.api.enumeration.RestricaoAlimentarEnum;
import br.org.fepb.api.enumeration.TipoPessoaEnum;
import br.org.fepb.api.enumeration.TipoSanguineoEnum;

import java.text.SimpleDateFormat;

public class PessoaDTO {

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    private Long id;

    private String nome;

    private String comoChamar;

    private String tipoPessoa;

    private String sexo;

    private String tipoSanguineo;

    private String cpf;

    private String cnpj;

    private String email;

    private String dataNascimento;

    private String restricaoSaude;

    private String restricaoAlimentar;

    public PessoaDTO() { }

    public PessoaDTO(Pessoa p) {
        this.id = p.getId();
        this.nome = p.getNome();
        this.comoChamar = p.getComoChamar();

        if (TipoPessoaEnum.FISICA.equals(p.getTipoPessoa())) {
            this.tipoPessoa = TipoPessoaEnum.FISICA.toString();
        } else if (TipoPessoaEnum.JURIDICA.equals(p.getTipoPessoa())) {
            this.tipoPessoa = TipoPessoaEnum.JURIDICA.toString();
        }

        if (p.getSexo() != null) {
            this.sexo = p.getSexo().toString();
        }

        if (p.getTipoSanguineo() != null && TipoSanguineoEnum.A_POSITIVO
            .equals(p.getTipoSanguineo())) {
            this.tipoSanguineo = TipoSanguineoEnum.A_POSITIVO.toString();
        } else if (p.getTipoSanguineo() != null && TipoSanguineoEnum.A_NEGATIVO
            .equals(p.getTipoSanguineo())) {
            this.tipoSanguineo = TipoSanguineoEnum.A_NEGATIVO.toString();
        } else if (p.getTipoSanguineo() != null && TipoSanguineoEnum.B_POSITIVO
            .equals(p.getTipoSanguineo())) {
            this.tipoSanguineo = TipoSanguineoEnum.B_POSITIVO.toString();
        } else if (p.getTipoSanguineo() != null && TipoSanguineoEnum.B_NEGATIVO
            .equals(p.getTipoSanguineo())) {
            this.tipoSanguineo = TipoSanguineoEnum.B_NEGATIVO.toString();
        } else if (p.getTipoSanguineo() != null && TipoSanguineoEnum.AB_POSITIVO
            .equals(p.getTipoSanguineo())) {
            this.tipoSanguineo = TipoSanguineoEnum.AB_POSITIVO.toString();
        } else if (p.getTipoSanguineo() != null && TipoSanguineoEnum.AB_NEGATIVO
            .equals(p.getTipoSanguineo())) {
            this.tipoSanguineo = TipoSanguineoEnum.AB_NEGATIVO.toString();
        } else if (p.getTipoSanguineo() != null && TipoSanguineoEnum.O_POSITIVO
            .equals(p.getTipoSanguineo())) {
            this.tipoSanguineo = TipoSanguineoEnum.O_POSITIVO.toString();
        } else if (p.getTipoSanguineo() != null && TipoSanguineoEnum.O_NEGATIVO
            .equals(p.getTipoSanguineo())) {
            this.tipoSanguineo = TipoSanguineoEnum.O_NEGATIVO.toString();
        }

        if (p.getTipoPessoa() != null && TipoPessoaEnum.FISICA.equals(p.getTipoPessoa())) {
            this.cpf = p.getCpf();
        } else if (p.getTipoPessoa() != null && TipoPessoaEnum.JURIDICA.equals(p.getTipoPessoa())) {
            this.cnpj = p.getCnpj();
        }

        this.email = p.getEmail();
        this.dataNascimento = formatter.format(p.getDataNascimento());
        this.restricaoSaude = p.getRestricaoSaude();

        if (p.getRestricaoAlimentar() != null && RestricaoAlimentarEnum.COME_CARNE.equals(p.getRestricaoAlimentar())) {
            this.restricaoAlimentar = RestricaoAlimentarEnum.COME_CARNE.toString();
        } else if (p.getRestricaoAlimentar() != null && RestricaoAlimentarEnum.NAO_COME_CARNE_VERMELHA.equals(p.getRestricaoAlimentar())) {
            this.restricaoAlimentar = RestricaoAlimentarEnum.NAO_COME_CARNE_VERMELHA.toString();
        } else if (p.getRestricaoAlimentar() != null && RestricaoAlimentarEnum.VEGETARIANO.equals(p.getRestricaoAlimentar())) {
            this.restricaoAlimentar = RestricaoAlimentarEnum.VEGETARIANO.toString();
        } else if (p.getRestricaoAlimentar() != null && RestricaoAlimentarEnum.VEGANO.equals(p.getRestricaoAlimentar())) {
            this.restricaoAlimentar = RestricaoAlimentarEnum.VEGANO.toString();
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

    public String getComoChamar() {
        return comoChamar;
    }

    public void setComoChamar(String comoChamar) {
        this.comoChamar = comoChamar;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getRestricaoSaude() {
        return restricaoSaude;
    }

    public void setRestricaoSaude(String restricaoSaude) {
        this.restricaoSaude = restricaoSaude;
    }

    public String getRestricaoAlimentar() {
        return restricaoAlimentar;
    }

    public void setRestricaoAlimentar(String restricaoAlimentar) {
        this.restricaoAlimentar = restricaoAlimentar;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
