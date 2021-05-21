package com.mydomain.accounting.model.common;

import java.math.BigDecimal;
import java.util.List;

public class RecordCommonModel {
    Long id;
    Long userId;
    BigDecimal amount;
    String note;
    String category;
    String status;
    List<TagCommonModel> tagList;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TagCommonModel> getTagList() {
        return tagList;
    }

    public void setTagList(List<TagCommonModel> tagList) {
        this.tagList = tagList;
    }
}
