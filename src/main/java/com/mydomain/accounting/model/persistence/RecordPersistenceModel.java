package com.mydomain.accounting.model.persistence;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class RecordPersistenceModel {
    Long id;
    Long userId;
    Integer status;
    Integer category;
    BigDecimal amount;
    String note;
    List<TagPersistenceModel> tagList;
    Instant createTime;
    Instant updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<TagPersistenceModel> getTagList() {
        return tagList;
    }

    public void setTagList(List<TagPersistenceModel> tagList) {
        this.tagList = tagList;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }
}
