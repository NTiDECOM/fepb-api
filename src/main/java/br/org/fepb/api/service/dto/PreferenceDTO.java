package br.org.fepb.api.service.dto;

import com.mercadopago.resources.Preference;

public class PreferenceDTO {

    private String id;

    private Boolean expires;

    private Integer quantity;

    private String title;

    private Float unitPrice;

    private String initPoint;

    private String sandboxInitPoint;

    private String externalReference;

    public PreferenceDTO() {}

    public PreferenceDTO(Preference p) {
        this.id = p.getId();
        this.expires = p.getExpires();
        this.unitPrice = p.getItems().get(0).getUnitPrice();
        this.initPoint = p.getInitPoint();
        this.sandboxInitPoint = p.getSandboxInitPoint();
        this.externalReference = p.getExternalReference();
    }

    public Boolean getExpires() {
        return expires;
    }

    public void setExpires(Boolean expires) {
        this.expires = expires;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }
}
