package com.mydomain.accounting.model.common;

public class TagCommonModel {
    Long id;
    String description;
    String status;
    Long userId;

    public TagCommonModel() {
    }

    public TagCommonModel(Long id, String description, String status, Long userId) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
