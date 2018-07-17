package br.org.fepb.api.service.dto;

import br.org.fepb.api.domain.Pessoa;
import br.org.fepb.api.enumeration.RestricaoAlimentarEnum;
import br.org.fepb.api.enumeration.TipoSanguineoEnum;

public class PessoaDTO {

    private Long id;

    private String nome;

    private String comoChamar;

    private String sexo;

    private String tipoSanguineo;

    private String cpf;

    private String email;

    private String dataNascimento;

    private String restricaoSaude;

    private String restricaoAlimentar;

    public PessoaDTO() { }

    public PessoaDTO(Pessoa p) {
        this.id = p.getId();
        this.nome = p.getNome();
        this.comoChamar = p.getComoChamar();

        this.sexo = p.getSexo().toString();
        if (TipoSanguineoEnum.A_POSITIVO.toString()
            .equals(p.getTipoSanguineo())) {
            this.tipoSanguineo = TipoSanguineoEnum.A_POSITIVO.toString();
        } else if (TipoSanguineoEnum.A_NEGATIVO.toString()
            .equals(p.getTipoSanguineo())) {
            this.tipoSanguineo = TipoSanguineoEnum.A_NEGATIVO.toString();
        } else if (TipoSanguineoEnum.B_POSITIVO.toString()
            .equals(p.getTipoSanguineo())) {
            this.tipoSanguineo = TipoSanguineoEnum.B_POSITIVO.toString();
        } else if (TipoSanguineoEnum.B_NEGATIVO.toString()
            .equals(p.getTipoSanguineo())) {
            this.tipoSanguineo = TipoSanguineoEnum.B_NEGATIVO.toString();
        } else if (TipoSanguineoEnum.AB_POSITIVO.toString()
            .equals(p.getTipoSanguineo())) {
            this.tipoSanguineo = TipoSanguineoEnum.AB_POSITIVO.toString();
        } else if (TipoSanguineoEnum.AB_NEGATIVO.toString()
            .equals(p.getTipoSanguineo())) {
            this.tipoSanguineo = TipoSanguineoEnum.AB_NEGATIVO.toString();
        } else if (TipoSanguineoEnum.O_POSITIVO.toString()
            .equals(p.getTipoSanguineo())) {
            this.tipoSanguineo = TipoSanguineoEnum.O_POSITIVO.toString();
        } else if (TipoSanguineoEnum.O_NEGATIVO.toString()
            .equals(p.getTipoSanguineo())) {
            this.tipoSanguineo = TipoSanguineoEnum.O_NEGATIVO.toString();
        }

        this.cpf = p.getCpf();
        this.email = p.getEmail();
        this.dataNascimento = p.getDataNascimento().toString();
        this.restricaoSaude = p.getRestricaoSaude();

        if (RestricaoAlimentarEnum.COME_CARNE.toString().equals(p.getRestricaoAlimentar())) {
            this.restricaoAlimentar = RestricaoAlimentarEnum.COME_CARNE.toString();
        } else if (RestricaoAlimentarEnum.NAO_COME_CARNE_VERMELHA.toString().equals(p.getRestricaoAlimentar())) {
            this.restricaoAlimentar = RestricaoAlimentarEnum.NAO_COME_CARNE_VERMELHA.toString();
        } else if (RestricaoAlimentarEnum.VEGETARIANO.toString().equals(p.getRestricaoAlimentar())) {
            this.restricaoAlimentar = RestricaoAlimentarEnum.VEGETARIANO.toString();
        } else if (RestricaoAlimentarEnum.VEGANO.toString().equals(p.getRestricaoAlimentar())) {
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
}
