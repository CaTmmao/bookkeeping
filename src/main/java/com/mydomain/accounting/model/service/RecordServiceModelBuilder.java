package com.mydomain.accounting.model.service;

import java.math.BigDecimal;
import java.util.List;

public final class RecordServiceModelBuilder {
    Long id;
    Long userId;
    BigDecimal amount;
    String note;
    String category;
    List<TagServiceModel> tagList;

    private RecordServiceModelBuilder() {
    }

    public static RecordServiceModelBuilder builder() {
        return new RecordServiceModelBuilder();
    }

    public RecordServiceModelBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public RecordServiceModelBuilder userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public RecordServiceModelBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public RecordServiceModelBuilder note(String note) {
        this.note = note;
        return this;
    }

    public RecordServiceModelBuilder category(String category) {
        this.category = category;
        return this;
    }

    public RecordServiceModelBuilder tagList(List<TagServiceModel> tagList) {
        this.tagList = tagList;
        return this;
    }

    public RecordServiceModel build() {
        RecordServiceModel recordServiceModel = new RecordServiceModel();
        recordServiceModel.setId(id);
        recordServiceModel.setUserId(userId);
        recordServiceModel.setAmount(amount);
        recordServiceModel.setNote(note);
        recordServiceModel.setCategory(category);
        recordServiceModel.setTagList(tagList);
        return recordServiceModel;
    }
}
