package br.org.fepb.api.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IpnDTO {

    private Long id;

    @JsonProperty("live_mode")
    private String liveMode;

    private String type;

    @JsonProperty("date_created")
    private String dateCreated;

    @JsonProperty("application_id")
    private String applicationId;

    @JsonProperty("user_id")
    private String userId;

    private String version;

    @JsonProperty("api_version")
    private String apiVersion;

    private String action;

    private IpnDataDTO data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLiveMode() {
        return liveMode;
    }

    public void setLiveMode(String liveMode) {
        this.liveMode = liveMode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public IpnDataDTO getData() {
        return data;
    }

    public void setData(IpnDataDTO data) {
        this.data = data;
    }
}
