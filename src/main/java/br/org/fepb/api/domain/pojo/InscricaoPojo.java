package br.org.fepb.api.domain.pojo;

import java.util.Date;

public class InscricaoPojo {

    public String nome;
    public Date dataNascimento;
    public String pagamento;

    public InscricaoPojo() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }
}
