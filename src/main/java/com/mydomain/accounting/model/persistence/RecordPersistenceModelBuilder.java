package com.mydomain.accounting.model.persistence;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public final class RecordPersistenceModelBuilder {
    Long id;
    Long userId;
    Integer status;
    Integer category;
    BigDecimal amount;
    String note;
    List<TagPersistenceModel> tagList;
    Instant createTime;
    Instant updateTime;

    private RecordPersistenceModelBuilder() {
    }

    public static RecordPersistenceModelBuilder builder() {
        return new RecordPersistenceModelBuilder();
    }

    public RecordPersistenceModelBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public RecordPersistenceModelBuilder userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public RecordPersistenceModelBuilder status(Integer status) {
        this.status = status;
        return this;
    }

    public RecordPersistenceModelBuilder category(Integer category) {
        this.category = category;
        return this;
    }

    public RecordPersistenceModelBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public RecordPersistenceModelBuilder note(String note) {
        this.note = note;
        return this;
    }

    public RecordPersistenceModelBuilder tagList(List<TagPersistenceModel> tagList) {
        this.tagList = tagList;
        return this;
    }

    public RecordPersistenceModelBuilder createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public RecordPersistenceModelBuilder updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public RecordPersistenceModel build() {
        RecordPersistenceModel recordPersistenceModel = new RecordPersistenceModel();
        recordPersistenceModel.setId(id);
        recordPersistenceModel.setUserId(userId);
        recordPersistenceModel.setStatus(status);
        recordPersistenceModel.setCategory(category);
        recordPersistenceModel.setAmount(amount);
        recordPersistenceModel.setNote(note);
        recordPersistenceModel.setTagList(tagList);
        recordPersistenceModel.setCreateTime(createTime);
        recordPersistenceModel.setUpdateTime(updateTime);
        return recordPersistenceModel;
    }
}
