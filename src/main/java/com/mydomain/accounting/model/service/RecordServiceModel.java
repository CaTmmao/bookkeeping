package com.mydomain.accounting.model.service;

import java.math.BigDecimal;
import java.util.List;

public class RecordServiceModel {
    Long id;
    Long userId;
    BigDecimal amount;
    String note;
    String category;
    List<TagServiceModel> tagList;

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

    public List<TagServiceModel> getTagList() {
        return tagList;
    }

    public void setTagList(List<TagServiceModel> tagList) {
        this.tagList = tagList;
    }
}
