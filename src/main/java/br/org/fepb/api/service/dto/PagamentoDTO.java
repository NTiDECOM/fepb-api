package br.org.fepb.api.service.dto;

import br.org.fepb.api.domain.Pagamento;

public class PagamentoDTO {

    private Long id;

    private String referenceId;

    private Float unitPrice;

    private String initPoint;

    private String sandboxInitPoint;

    private String status;

    public PagamentoDTO(Pagamento p) {
        this.id = p.getId();
        this.referenceId = p.getReferenceId();
        this.unitPrice = p.getUnitPrice();
        this.initPoint = p.getInitPoint();
        this.sandboxInitPoint = p.getSandboxInitPoint();
        this.status = p.getStatus();
    }

    public PagamentoDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getInitPoint() {
        return initPoint;
    }

    public void setInitPoint(String initPoint) {
        this.initPoint = initPoint;
    }

    public String getSandboxInitPoint() {
        return sandboxInitPoint;
    }

    public void setSandboxInitPoint(String sandboxInitPoint) {
        this.sandboxInitPoint = sandboxInitPoint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
