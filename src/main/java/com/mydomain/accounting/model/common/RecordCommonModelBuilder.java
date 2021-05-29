package com.mydomain.accounting.model.common;

import java.math.BigDecimal;
import java.util.List;

public final class RecordCommonModelBuilder {
    Long id;
    Long userId;
    BigDecimal amount;
    String note;
    String category;
    String status;
    List<TagCommonModel> tagList;

    private RecordCommonModelBuilder() {
    }

    public static RecordCommonModelBuilder builder() {
        return new RecordCommonModelBuilder();
    }

    public RecordCommonModelBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public RecordCommonModelBuilder userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public RecordCommonModelBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public RecordCommonModelBuilder note(String note) {
        this.note = note;
        return this;
    }

    public RecordCommonModelBuilder category(String category) {
        this.category = category;
        return this;
    }

    public RecordCommonModelBuilder status(String status) {
        this.status = status;
        return this;
    }

    public RecordCommonModelBuilder tagList(List<TagCommonModel> tagList) {
        this.tagList = tagList;
        return this;
    }

    public RecordCommonModel build() {
        return new RecordCommonModel(id, userId, amount, note, category, status, tagList);
    }
}
