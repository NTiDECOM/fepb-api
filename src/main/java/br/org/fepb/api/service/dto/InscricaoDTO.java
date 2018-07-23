package br.org.fepb.api.service.dto;

import br.org.fepb.api.domain.Inscricao;

public class InscricaoDTO {

    private Long id;

    private boolean trabalhador;

    private String telefone;

    private String instituicao;

    private String nomeCoordenador;

    private String emailCoordenador;

    private PessoaDTO pessoa;

    private OficinaDTO oficina;

    private boolean pago;

    public InscricaoDTO() { }

    public InscricaoDTO(Inscricao i) {

        this.id = i.getId();
        this.trabalhador = i.getTrabalhador().booleanValue();
        this.telefone = i.getTelefone();
        this.instituicao = i.getInstituicao();
        this.nomeCoordenador = i.getNomeCoordenador();
        this.emailCoordenador = i.getEmailCoordenador();
        this.pessoa = new PessoaDTO(i.getPessoa());
        this.oficina = new OficinaDTO(i.getOficina());
        this.pago = i.getPago().booleanValue();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isTrabalhador() {
        return trabalhador;
    }

    public void setTrabalhador(boolean trabalhador) {
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

    public PessoaDTO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaDTO pessoa) {
        this.pessoa = pessoa;
    }

    public OficinaDTO getOficina() {
        return oficina;
    }

    public void setOficina(OficinaDTO oficina) {
        this.oficina = oficina;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }
}
