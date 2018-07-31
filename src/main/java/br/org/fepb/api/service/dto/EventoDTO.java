package br.org.fepb.api.service.dto;

import br.org.fepb.api.domain.Departamento;
import br.org.fepb.api.domain.Evento;

import java.util.ArrayList;
import java.util.List;

public class EventoDTO {

    private Long id;

    private String nome;

    private String tematica;
    private String dataInicio;

    private String dataFim;

    private String horaInicio;
    private String horaFim;

    private String tipo;

    private LocalDTO local;

    private String enderecoInscricao;

    private String contato;

    private String estimativa;

    private String objetivo;

    private String alvo;

    private List<DepartamentoDTO> departamentos = new ArrayList<DepartamentoDTO>();

    public EventoDTO() {}

    public EventoDTO(Evento e) {
        this.id = e.getId();
        this.nome = e.getNome();
        this.tematica = e.getTematica();
        this.dataInicio = e.getDataInicio().toString();
        this.dataFim = e.getDataFim().toString();
        this.horaInicio = e.getHoraInicio();
        this.horaFim = e.getHoraFim();
        this.tipo = e.getTipo().toString();
        this.local = new LocalDTO(e.getLocal());
        this.enderecoInscricao = e.getEnderecoInscricao();
        this.contato = e.getContato();
        this.estimativa = e.getEstimativa();
        this.objetivo = e.getObjetivo();
        this.alvo = e.getAlvo();

        for (Departamento d : e.getDepartamentos()) {
            DepartamentoDTO dto = new DepartamentoDTO(d);
            this.departamentos.add(dto);
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

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDTO getLocal() {
        return local;
    }

    public void setLocal(LocalDTO local) {
        this.local = local;
    }

    public String getEnderecoInscricao() {
        return enderecoInscricao;
    }

    public void setEnderecoInscricao(String enderecoInscricao) {
        this.enderecoInscricao = enderecoInscricao;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEstimativa() {
        return estimativa;
    }

    public void setEstimativa(String estimativa) {
        this.estimativa = estimativa;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getAlvo() {
        return alvo;
    }

    public void setAlvo(String alvo) {
        this.alvo = alvo;
    }

    public List<DepartamentoDTO> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<DepartamentoDTO> departamentos) {
        this.departamentos = departamentos;
    }
}
